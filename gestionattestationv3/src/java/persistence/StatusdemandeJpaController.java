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
import entitie.Demande;
import entitie.Statusdemande;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import persistence.exceptions.NonexistentEntityException;
import persistence.exceptions.PreexistingEntityException;
import persistence.exceptions.RollbackFailureException;

/**
 *
 * @author YASSALIE
 */
public class StatusdemandeJpaController implements Serializable {

    public StatusdemandeJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Statusdemande statusdemande) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (statusdemande.getDemandeList() == null) {
            statusdemande.setDemandeList(new ArrayList<Demande>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Demande> attachedDemandeList = new ArrayList<Demande>();
            for (Demande demandeListDemandeToAttach : statusdemande.getDemandeList()) {
                demandeListDemandeToAttach = em.getReference(demandeListDemandeToAttach.getClass(), demandeListDemandeToAttach.getId());
                attachedDemandeList.add(demandeListDemandeToAttach);
            }
            statusdemande.setDemandeList(attachedDemandeList);
            em.persist(statusdemande);
            for (Demande demandeListDemande : statusdemande.getDemandeList()) {
                Statusdemande oldEtatOfDemandeListDemande = demandeListDemande.getEtat();
                demandeListDemande.setEtat(statusdemande);
                demandeListDemande = em.merge(demandeListDemande);
                if (oldEtatOfDemandeListDemande != null) {
                    oldEtatOfDemandeListDemande.getDemandeList().remove(demandeListDemande);
                    oldEtatOfDemandeListDemande = em.merge(oldEtatOfDemandeListDemande);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findStatusdemande(statusdemande.getId()) != null) {
                throw new PreexistingEntityException("Statusdemande " + statusdemande + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Statusdemande statusdemande) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Statusdemande persistentStatusdemande = em.find(Statusdemande.class, statusdemande.getId());
            List<Demande> demandeListOld = persistentStatusdemande.getDemandeList();
            List<Demande> demandeListNew = statusdemande.getDemandeList();
            List<Demande> attachedDemandeListNew = new ArrayList<Demande>();
            for (Demande demandeListNewDemandeToAttach : demandeListNew) {
                demandeListNewDemandeToAttach = em.getReference(demandeListNewDemandeToAttach.getClass(), demandeListNewDemandeToAttach.getId());
                attachedDemandeListNew.add(demandeListNewDemandeToAttach);
            }
            demandeListNew = attachedDemandeListNew;
            statusdemande.setDemandeList(demandeListNew);
            statusdemande = em.merge(statusdemande);
            for (Demande demandeListOldDemande : demandeListOld) {
                if (!demandeListNew.contains(demandeListOldDemande)) {
                    demandeListOldDemande.setEtat(null);
                    demandeListOldDemande = em.merge(demandeListOldDemande);
                }
            }
            for (Demande demandeListNewDemande : demandeListNew) {
                if (!demandeListOld.contains(demandeListNewDemande)) {
                    Statusdemande oldEtatOfDemandeListNewDemande = demandeListNewDemande.getEtat();
                    demandeListNewDemande.setEtat(statusdemande);
                    demandeListNewDemande = em.merge(demandeListNewDemande);
                    if (oldEtatOfDemandeListNewDemande != null && !oldEtatOfDemandeListNewDemande.equals(statusdemande)) {
                        oldEtatOfDemandeListNewDemande.getDemandeList().remove(demandeListNewDemande);
                        oldEtatOfDemandeListNewDemande = em.merge(oldEtatOfDemandeListNewDemande);
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
                Integer id = statusdemande.getId();
                if (findStatusdemande(id) == null) {
                    throw new NonexistentEntityException("The statusdemande with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Statusdemande statusdemande;
            try {
                statusdemande = em.getReference(Statusdemande.class, id);
                statusdemande.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The statusdemande with id " + id + " no longer exists.", enfe);
            }
            List<Demande> demandeList = statusdemande.getDemandeList();
            for (Demande demandeListDemande : demandeList) {
                demandeListDemande.setEtat(null);
                demandeListDemande = em.merge(demandeListDemande);
            }
            em.remove(statusdemande);
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

    public List<Statusdemande> findStatusdemandeEntities() {
        return findStatusdemandeEntities(true, -1, -1);
    }

    public List<Statusdemande> findStatusdemandeEntities(int maxResults, int firstResult) {
        return findStatusdemandeEntities(false, maxResults, firstResult);
    }

    private List<Statusdemande> findStatusdemandeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Statusdemande.class));
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

    public Statusdemande findStatusdemande(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Statusdemande.class, id);
        } finally {
            em.close();
        }
    }

    public int getStatusdemandeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Statusdemande> rt = cq.from(Statusdemande.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
