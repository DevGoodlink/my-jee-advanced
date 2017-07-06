/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dao.exceptions.RollbackFailureException;
import entitylayer.Stage;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entitylayer.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author YASSALIE
 */
public class StageJpaController implements Serializable {

    public StageJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Stage stage) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            User idEncadrant = stage.getIdEncadrant();
            if (idEncadrant != null) {
                idEncadrant = em.getReference(idEncadrant.getClass(), idEncadrant.getId());
                stage.setIdEncadrant(idEncadrant);
            }
            User idStagiaire = stage.getIdStagiaire();
            if (idStagiaire != null) {
                idStagiaire = em.getReference(idStagiaire.getClass(), idStagiaire.getId());
                stage.setIdStagiaire(idStagiaire);
            }
            em.persist(stage);
            if (idEncadrant != null) {
                idEncadrant.getStageCollection().add(stage);
                idEncadrant = em.merge(idEncadrant);
            }
            if (idStagiaire != null) {
                idStagiaire.getStageCollection().add(stage);
                idStagiaire = em.merge(idStagiaire);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findStage(stage.getId()) != null) {
                throw new PreexistingEntityException("Stage " + stage + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Stage stage) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Stage persistentStage = em.find(Stage.class, stage.getId());
            User idEncadrantOld = persistentStage.getIdEncadrant();
            User idEncadrantNew = stage.getIdEncadrant();
            User idStagiaireOld = persistentStage.getIdStagiaire();
            User idStagiaireNew = stage.getIdStagiaire();
            if (idEncadrantNew != null) {
                idEncadrantNew = em.getReference(idEncadrantNew.getClass(), idEncadrantNew.getId());
                stage.setIdEncadrant(idEncadrantNew);
            }
            if (idStagiaireNew != null) {
                idStagiaireNew = em.getReference(idStagiaireNew.getClass(), idStagiaireNew.getId());
                stage.setIdStagiaire(idStagiaireNew);
            }
            stage = em.merge(stage);
            if (idEncadrantOld != null && !idEncadrantOld.equals(idEncadrantNew)) {
                idEncadrantOld.getStageCollection().remove(stage);
                idEncadrantOld = em.merge(idEncadrantOld);
            }
            if (idEncadrantNew != null && !idEncadrantNew.equals(idEncadrantOld)) {
                idEncadrantNew.getStageCollection().add(stage);
                idEncadrantNew = em.merge(idEncadrantNew);
            }
            if (idStagiaireOld != null && !idStagiaireOld.equals(idStagiaireNew)) {
                idStagiaireOld.getStageCollection().remove(stage);
                idStagiaireOld = em.merge(idStagiaireOld);
            }
            if (idStagiaireNew != null && !idStagiaireNew.equals(idStagiaireOld)) {
                idStagiaireNew.getStageCollection().add(stage);
                idStagiaireNew = em.merge(idStagiaireNew);
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
                Integer id = stage.getId();
                if (findStage(id) == null) {
                    throw new NonexistentEntityException("The stage with id " + id + " no longer exists.");
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
            Stage stage;
            try {
                stage = em.getReference(Stage.class, id);
                stage.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The stage with id " + id + " no longer exists.", enfe);
            }
            User idEncadrant = stage.getIdEncadrant();
            if (idEncadrant != null) {
                idEncadrant.getStageCollection().remove(stage);
                idEncadrant = em.merge(idEncadrant);
            }
            User idStagiaire = stage.getIdStagiaire();
            if (idStagiaire != null) {
                idStagiaire.getStageCollection().remove(stage);
                idStagiaire = em.merge(idStagiaire);
            }
            em.remove(stage);
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

    public List<Stage> findStageEntities() {
        return findStageEntities(true, -1, -1);
    }

    public List<Stage> findStageEntities(int maxResults, int firstResult) {
        return findStageEntities(false, maxResults, firstResult);
    }

    private List<Stage> findStageEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Stage.class));
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

    public Stage findStage(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Stage.class, id);
        } finally {
            em.close();
        }
    }

    public int getStageCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Stage> rt = cq.from(Stage.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
