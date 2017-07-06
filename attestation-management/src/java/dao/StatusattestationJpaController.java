/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.IllegalOrphanException;
import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dao.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entitylayer.Attestation;
import entitylayer.Statusattestation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

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
        if (statusattestation.getAttestationCollection() == null) {
            statusattestation.setAttestationCollection(new ArrayList<Attestation>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Attestation> attachedAttestationCollection = new ArrayList<Attestation>();
            for (Attestation attestationCollectionAttestationToAttach : statusattestation.getAttestationCollection()) {
                attestationCollectionAttestationToAttach = em.getReference(attestationCollectionAttestationToAttach.getClass(), attestationCollectionAttestationToAttach.getId());
                attachedAttestationCollection.add(attestationCollectionAttestationToAttach);
            }
            statusattestation.setAttestationCollection(attachedAttestationCollection);
            em.persist(statusattestation);
            for (Attestation attestationCollectionAttestation : statusattestation.getAttestationCollection()) {
                Statusattestation oldIdStatutOfAttestationCollectionAttestation = attestationCollectionAttestation.getIdStatut();
                attestationCollectionAttestation.setIdStatut(statusattestation);
                attestationCollectionAttestation = em.merge(attestationCollectionAttestation);
                if (oldIdStatutOfAttestationCollectionAttestation != null) {
                    oldIdStatutOfAttestationCollectionAttestation.getAttestationCollection().remove(attestationCollectionAttestation);
                    oldIdStatutOfAttestationCollectionAttestation = em.merge(oldIdStatutOfAttestationCollectionAttestation);
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
            Collection<Attestation> attestationCollectionOld = persistentStatusattestation.getAttestationCollection();
            Collection<Attestation> attestationCollectionNew = statusattestation.getAttestationCollection();
            List<String> illegalOrphanMessages = null;
            for (Attestation attestationCollectionOldAttestation : attestationCollectionOld) {
                if (!attestationCollectionNew.contains(attestationCollectionOldAttestation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Attestation " + attestationCollectionOldAttestation + " since its idStatut field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Attestation> attachedAttestationCollectionNew = new ArrayList<Attestation>();
            for (Attestation attestationCollectionNewAttestationToAttach : attestationCollectionNew) {
                attestationCollectionNewAttestationToAttach = em.getReference(attestationCollectionNewAttestationToAttach.getClass(), attestationCollectionNewAttestationToAttach.getId());
                attachedAttestationCollectionNew.add(attestationCollectionNewAttestationToAttach);
            }
            attestationCollectionNew = attachedAttestationCollectionNew;
            statusattestation.setAttestationCollection(attestationCollectionNew);
            statusattestation = em.merge(statusattestation);
            for (Attestation attestationCollectionNewAttestation : attestationCollectionNew) {
                if (!attestationCollectionOld.contains(attestationCollectionNewAttestation)) {
                    Statusattestation oldIdStatutOfAttestationCollectionNewAttestation = attestationCollectionNewAttestation.getIdStatut();
                    attestationCollectionNewAttestation.setIdStatut(statusattestation);
                    attestationCollectionNewAttestation = em.merge(attestationCollectionNewAttestation);
                    if (oldIdStatutOfAttestationCollectionNewAttestation != null && !oldIdStatutOfAttestationCollectionNewAttestation.equals(statusattestation)) {
                        oldIdStatutOfAttestationCollectionNewAttestation.getAttestationCollection().remove(attestationCollectionNewAttestation);
                        oldIdStatutOfAttestationCollectionNewAttestation = em.merge(oldIdStatutOfAttestationCollectionNewAttestation);
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
            Collection<Attestation> attestationCollectionOrphanCheck = statusattestation.getAttestationCollection();
            for (Attestation attestationCollectionOrphanCheckAttestation : attestationCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Statusattestation (" + statusattestation + ") cannot be destroyed since the Attestation " + attestationCollectionOrphanCheckAttestation + " in its attestationCollection field has a non-nullable idStatut field.");
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
