/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dao.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entitylayer.Demande;
import entitylayer.Statusdemande;
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
        if (statusdemande.getDemandeCollection() == null) {
            statusdemande.setDemandeCollection(new ArrayList<Demande>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Demande> attachedDemandeCollection = new ArrayList<Demande>();
            for (Demande demandeCollectionDemandeToAttach : statusdemande.getDemandeCollection()) {
                demandeCollectionDemandeToAttach = em.getReference(demandeCollectionDemandeToAttach.getClass(), demandeCollectionDemandeToAttach.getId());
                attachedDemandeCollection.add(demandeCollectionDemandeToAttach);
            }
            statusdemande.setDemandeCollection(attachedDemandeCollection);
            em.persist(statusdemande);
            for (Demande demandeCollectionDemande : statusdemande.getDemandeCollection()) {
                Statusdemande oldEtatOfDemandeCollectionDemande = demandeCollectionDemande.getEtat();
                demandeCollectionDemande.setEtat(statusdemande);
                demandeCollectionDemande = em.merge(demandeCollectionDemande);
                if (oldEtatOfDemandeCollectionDemande != null) {
                    oldEtatOfDemandeCollectionDemande.getDemandeCollection().remove(demandeCollectionDemande);
                    oldEtatOfDemandeCollectionDemande = em.merge(oldEtatOfDemandeCollectionDemande);
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
            Collection<Demande> demandeCollectionOld = persistentStatusdemande.getDemandeCollection();
            Collection<Demande> demandeCollectionNew = statusdemande.getDemandeCollection();
            Collection<Demande> attachedDemandeCollectionNew = new ArrayList<Demande>();
            for (Demande demandeCollectionNewDemandeToAttach : demandeCollectionNew) {
                demandeCollectionNewDemandeToAttach = em.getReference(demandeCollectionNewDemandeToAttach.getClass(), demandeCollectionNewDemandeToAttach.getId());
                attachedDemandeCollectionNew.add(demandeCollectionNewDemandeToAttach);
            }
            demandeCollectionNew = attachedDemandeCollectionNew;
            statusdemande.setDemandeCollection(demandeCollectionNew);
            statusdemande = em.merge(statusdemande);
            for (Demande demandeCollectionOldDemande : demandeCollectionOld) {
                if (!demandeCollectionNew.contains(demandeCollectionOldDemande)) {
                    demandeCollectionOldDemande.setEtat(null);
                    demandeCollectionOldDemande = em.merge(demandeCollectionOldDemande);
                }
            }
            for (Demande demandeCollectionNewDemande : demandeCollectionNew) {
                if (!demandeCollectionOld.contains(demandeCollectionNewDemande)) {
                    Statusdemande oldEtatOfDemandeCollectionNewDemande = demandeCollectionNewDemande.getEtat();
                    demandeCollectionNewDemande.setEtat(statusdemande);
                    demandeCollectionNewDemande = em.merge(demandeCollectionNewDemande);
                    if (oldEtatOfDemandeCollectionNewDemande != null && !oldEtatOfDemandeCollectionNewDemande.equals(statusdemande)) {
                        oldEtatOfDemandeCollectionNewDemande.getDemandeCollection().remove(demandeCollectionNewDemande);
                        oldEtatOfDemandeCollectionNewDemande = em.merge(oldEtatOfDemandeCollectionNewDemande);
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
            Collection<Demande> demandeCollection = statusdemande.getDemandeCollection();
            for (Demande demandeCollectionDemande : demandeCollection) {
                demandeCollectionDemande.setEtat(null);
                demandeCollectionDemande = em.merge(demandeCollectionDemande);
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
