/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import entitie.Demande;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entitie.User;
import entitie.Statusdemande;
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
public class DemandeJpaController implements Serializable {

    public DemandeJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Demande demande) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            User userId = demande.getUserId();
            if (userId != null) {
                userId = em.getReference(userId.getClass(), userId.getId());
                demande.setUserId(userId);
            }
            Statusdemande etat = demande.getEtat();
            if (etat != null) {
                etat = em.getReference(etat.getClass(), etat.getId());
                demande.setEtat(etat);
            }
            em.persist(demande);
            if (userId != null) {
                userId.getDemandeList().add(demande);
                userId = em.merge(userId);
            }
            if (etat != null) {
                etat.getDemandeList().add(demande);
                etat = em.merge(etat);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findDemande(demande.getId()) != null) {
                throw new PreexistingEntityException("Demande " + demande + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Demande demande) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Demande persistentDemande = em.find(Demande.class, demande.getId());
            User userIdOld = persistentDemande.getUserId();
            User userIdNew = demande.getUserId();
            Statusdemande etatOld = persistentDemande.getEtat();
            Statusdemande etatNew = demande.getEtat();
            if (userIdNew != null) {
                userIdNew = em.getReference(userIdNew.getClass(), userIdNew.getId());
                demande.setUserId(userIdNew);
            }
            if (etatNew != null) {
                etatNew = em.getReference(etatNew.getClass(), etatNew.getId());
                demande.setEtat(etatNew);
            }
            demande = em.merge(demande);
            if (userIdOld != null && !userIdOld.equals(userIdNew)) {
                userIdOld.getDemandeList().remove(demande);
                userIdOld = em.merge(userIdOld);
            }
            if (userIdNew != null && !userIdNew.equals(userIdOld)) {
                userIdNew.getDemandeList().add(demande);
                userIdNew = em.merge(userIdNew);
            }
            if (etatOld != null && !etatOld.equals(etatNew)) {
                etatOld.getDemandeList().remove(demande);
                etatOld = em.merge(etatOld);
            }
            if (etatNew != null && !etatNew.equals(etatOld)) {
                etatNew.getDemandeList().add(demande);
                etatNew = em.merge(etatNew);
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
                Integer id = demande.getId();
                if (findDemande(id) == null) {
                    throw new NonexistentEntityException("The demande with id " + id + " no longer exists.");
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
            Demande demande;
            try {
                demande = em.getReference(Demande.class, id);
                demande.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The demande with id " + id + " no longer exists.", enfe);
            }
            User userId = demande.getUserId();
            if (userId != null) {
                userId.getDemandeList().remove(demande);
                userId = em.merge(userId);
            }
            Statusdemande etat = demande.getEtat();
            if (etat != null) {
                etat.getDemandeList().remove(demande);
                etat = em.merge(etat);
            }
            em.remove(demande);
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

    public List<Demande> findDemandeEntities() {
        return findDemandeEntities(true, -1, -1);
    }

    public List<Demande> findDemandeEntities(int maxResults, int firstResult) {
        return findDemandeEntities(false, maxResults, firstResult);
    }

    private List<Demande> findDemandeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Demande.class));
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

    public Demande findDemande(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Demande.class, id);
        } finally {
            em.close();
        }
    }

    public int getDemandeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Demande> rt = cq.from(Demande.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
