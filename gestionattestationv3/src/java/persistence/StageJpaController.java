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
import entitie.User;
import entitie.Attestation;
import entitie.Stage;
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
        if (stage.getAttestationList() == null) {
            stage.setAttestationList(new ArrayList<Attestation>());
        }
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
            List<Attestation> attachedAttestationList = new ArrayList<Attestation>();
            for (Attestation attestationListAttestationToAttach : stage.getAttestationList()) {
                attestationListAttestationToAttach = em.getReference(attestationListAttestationToAttach.getClass(), attestationListAttestationToAttach.getId());
                attachedAttestationList.add(attestationListAttestationToAttach);
            }
            stage.setAttestationList(attachedAttestationList);
            em.persist(stage);
            if (idEncadrant != null) {
                idEncadrant.getStageList().add(stage);
                idEncadrant = em.merge(idEncadrant);
            }
            if (idStagiaire != null) {
                idStagiaire.getStageList().add(stage);
                idStagiaire = em.merge(idStagiaire);
            }
            for (Attestation attestationListAttestation : stage.getAttestationList()) {
                Stage oldIdStageOfAttestationListAttestation = attestationListAttestation.getIdStage();
                attestationListAttestation.setIdStage(stage);
                attestationListAttestation = em.merge(attestationListAttestation);
                if (oldIdStageOfAttestationListAttestation != null) {
                    oldIdStageOfAttestationListAttestation.getAttestationList().remove(attestationListAttestation);
                    oldIdStageOfAttestationListAttestation = em.merge(oldIdStageOfAttestationListAttestation);
                }
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
            List<Attestation> attestationListOld = persistentStage.getAttestationList();
            List<Attestation> attestationListNew = stage.getAttestationList();
            if (idEncadrantNew != null) {
                idEncadrantNew = em.getReference(idEncadrantNew.getClass(), idEncadrantNew.getId());
                stage.setIdEncadrant(idEncadrantNew);
            }
            if (idStagiaireNew != null) {
                idStagiaireNew = em.getReference(idStagiaireNew.getClass(), idStagiaireNew.getId());
                stage.setIdStagiaire(idStagiaireNew);
            }
            List<Attestation> attachedAttestationListNew = new ArrayList<Attestation>();
            for (Attestation attestationListNewAttestationToAttach : attestationListNew) {
                attestationListNewAttestationToAttach = em.getReference(attestationListNewAttestationToAttach.getClass(), attestationListNewAttestationToAttach.getId());
                attachedAttestationListNew.add(attestationListNewAttestationToAttach);
            }
            attestationListNew = attachedAttestationListNew;
            stage.setAttestationList(attestationListNew);
            stage = em.merge(stage);
            if (idEncadrantOld != null && !idEncadrantOld.equals(idEncadrantNew)) {
                idEncadrantOld.getStageList().remove(stage);
                idEncadrantOld = em.merge(idEncadrantOld);
            }
            if (idEncadrantNew != null && !idEncadrantNew.equals(idEncadrantOld)) {
                idEncadrantNew.getStageList().add(stage);
                idEncadrantNew = em.merge(idEncadrantNew);
            }
            if (idStagiaireOld != null && !idStagiaireOld.equals(idStagiaireNew)) {
                idStagiaireOld.getStageList().remove(stage);
                idStagiaireOld = em.merge(idStagiaireOld);
            }
            if (idStagiaireNew != null && !idStagiaireNew.equals(idStagiaireOld)) {
                idStagiaireNew.getStageList().add(stage);
                idStagiaireNew = em.merge(idStagiaireNew);
            }
            for (Attestation attestationListOldAttestation : attestationListOld) {
                if (!attestationListNew.contains(attestationListOldAttestation)) {
                    attestationListOldAttestation.setIdStage(null);
                    attestationListOldAttestation = em.merge(attestationListOldAttestation);
                }
            }
            for (Attestation attestationListNewAttestation : attestationListNew) {
                if (!attestationListOld.contains(attestationListNewAttestation)) {
                    Stage oldIdStageOfAttestationListNewAttestation = attestationListNewAttestation.getIdStage();
                    attestationListNewAttestation.setIdStage(stage);
                    attestationListNewAttestation = em.merge(attestationListNewAttestation);
                    if (oldIdStageOfAttestationListNewAttestation != null && !oldIdStageOfAttestationListNewAttestation.equals(stage)) {
                        oldIdStageOfAttestationListNewAttestation.getAttestationList().remove(attestationListNewAttestation);
                        oldIdStageOfAttestationListNewAttestation = em.merge(oldIdStageOfAttestationListNewAttestation);
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
                idEncadrant.getStageList().remove(stage);
                idEncadrant = em.merge(idEncadrant);
            }
            User idStagiaire = stage.getIdStagiaire();
            if (idStagiaire != null) {
                idStagiaire.getStageList().remove(stage);
                idStagiaire = em.merge(idStagiaire);
            }
            List<Attestation> attestationList = stage.getAttestationList();
            for (Attestation attestationListAttestation : attestationList) {
                attestationListAttestation.setIdStage(null);
                attestationListAttestation = em.merge(attestationListAttestation);
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
