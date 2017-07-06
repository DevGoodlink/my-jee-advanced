/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entitie.Attestation;
import entitie.Statusattestation;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import persistence.exceptions.IllegalOrphanException;
import persistence.exceptions.NonexistentEntityException;
import persistence.exceptions.PreexistingEntityException;
import persistence.exceptions.RollbackFailureException;

/**
 *
 * @author YASSALIE
 */
public class StatusattestationJpaController implements Serializable {

    public StatusattestationJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Statusattestation statusattestation) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (statusattestation.getAttestationList() == null) {
            statusattestation.setAttestationList(new ArrayList<Attestation>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Attestation> attachedAttestationList = new ArrayList<Attestation>();
            for (Attestation attestationListAttestationToAttach : statusattestation.getAttestationList()) {
                attestationListAttestationToAttach = em.getReference(attestationListAttestationToAttach.getClass(), attestationListAttestationToAttach.getId());
                attachedAttestationList.add(attestationListAttestationToAttach);
            }
            statusattestation.setAttestationList(attachedAttestationList);
            em.persist(statusattestation);
            for (Attestation attestationListAttestation : statusattestation.getAttestationList()) {
                Statusattestation oldIdStatutOfAttestationListAttestation = attestationListAttestation.getIdStatut();
                attestationListAttestation.setIdStatut(statusattestation);
                attestationListAttestation = em.merge(attestationListAttestation);
                if (oldIdStatutOfAttestationListAttestation != null) {
                    oldIdStatutOfAttestationListAttestation.getAttestationList().remove(attestationListAttestation);
                    oldIdStatutOfAttestationListAttestation = em.merge(oldIdStatutOfAttestationListAttestation);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findStatusattestation(statusattestation.getId()) != null) {
                throw new PreexistingEntityException("Statusattestation " + statusattestation + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Statusattestation statusattestation) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Statusattestation persistentStatusattestation = em.find(Statusattestation.class, statusattestation.getId());
            List<Attestation> attestationListOld = persistentStatusattestation.getAttestationList();
            List<Attestation> attestationListNew = statusattestation.getAttestationList();
            List<String> illegalOrphanMessages = null;
            for (Attestation attestationListOldAttestation : attestationListOld) {
                if (!attestationListNew.contains(attestationListOldAttestation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Attestation " + attestationListOldAttestation + " since its idStatut field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Attestation> attachedAttestationListNew = new ArrayList<Attestation>();
            for (Attestation attestationListNewAttestationToAttach : attestationListNew) {
                attestationListNewAttestationToAttach = em.getReference(attestationListNewAttestationToAttach.getClass(), attestationListNewAttestationToAttach.getId());
                attachedAttestationListNew.add(attestationListNewAttestationToAttach);
            }
            attestationListNew = attachedAttestationListNew;
            statusattestation.setAttestationList(attestationListNew);
            statusattestation = em.merge(statusattestation);
            for (Attestation attestationListNewAttestation : attestationListNew) {
                if (!attestationListOld.contains(attestationListNewAttestation)) {
                    Statusattestation oldIdStatutOfAttestationListNewAttestation = attestationListNewAttestation.getIdStatut();
                    attestationListNewAttestation.setIdStatut(statusattestation);
                    attestationListNewAttestation = em.merge(attestationListNewAttestation);
                    if (oldIdStatutOfAttestationListNewAttestation != null && !oldIdStatutOfAttestationListNewAttestation.equals(statusattestation)) {
                        oldIdStatutOfAttestationListNewAttestation.getAttestationList().remove(attestationListNewAttestation);
                        oldIdStatutOfAttestationListNewAttestation = em.merge(oldIdStatutOfAttestationListNewAttestation);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = statusattestation.getId();
                if (findStatusattestation(id) == null) {
                    throw new NonexistentEntityException("The statusattestation with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Statusattestation statusattestation;
            try {
                statusattestation = em.getReference(Statusattestation.class, id);
                statusattestation.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The statusattestation with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Attestation> attestationListOrphanCheck = statusattestation.getAttestationList();
            for (Attestation attestationListOrphanCheckAttestation : attestationListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Statusattestation (" + statusattestation + ") cannot be destroyed since the Attestation " + attestationListOrphanCheckAttestation + " in its attestationList field has a non-nullable idStatut field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(statusattestation);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Statusattestation> findStatusattestationEntities() {
        return findStatusattestationEntities(true, -1, -1);
    }

    public List<Statusattestation> findStatusattestationEntities(int maxResults, int firstResult) {
        return findStatusattestationEntities(false, maxResults, firstResult);
    }

    private List<Statusattestation> findStatusattestationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Statusattestation.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Statusattestation findStatusattestation(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Statusattestation.class, id);
        } finally {
            em.close();
        }
    }

    public int getStatusattestationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Statusattestation> rt = cq.from(Statusattestation.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
