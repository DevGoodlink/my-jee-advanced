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
import java.util.ArrayList;
import java.util.Collection;
import entitylayer.Stage;
import entitylayer.Demande;
import entitylayer.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author YASSALIE
 */
public class UserJpaController implements Serializable {

    public UserJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(User user) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (user.getAttestationCollection() == null) {
            user.setAttestationCollection(new ArrayList<Attestation>());
        }
        if (user.getAttestationCollection1() == null) {
            user.setAttestationCollection1(new ArrayList<Attestation>());
        }
        if (user.getAttestationCollection2() == null) {
            user.setAttestationCollection2(new ArrayList<Attestation>());
        }
        if (user.getStageCollection() == null) {
            user.setStageCollection(new ArrayList<Stage>());
        }
        if (user.getStageCollection1() == null) {
            user.setStageCollection1(new ArrayList<Stage>());
        }
        if (user.getDemandeCollection() == null) {
            user.setDemandeCollection(new ArrayList<Demande>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Attestation> attachedAttestationCollection = new ArrayList<Attestation>();
            for (Attestation attestationCollectionAttestationToAttach : user.getAttestationCollection()) {
                attestationCollectionAttestationToAttach = em.getReference(attestationCollectionAttestationToAttach.getClass(), attestationCollectionAttestationToAttach.getId());
                attachedAttestationCollection.add(attestationCollectionAttestationToAttach);
            }
            user.setAttestationCollection(attachedAttestationCollection);
            Collection<Attestation> attachedAttestationCollection1 = new ArrayList<Attestation>();
            for (Attestation attestationCollection1AttestationToAttach : user.getAttestationCollection1()) {
                attestationCollection1AttestationToAttach = em.getReference(attestationCollection1AttestationToAttach.getClass(), attestationCollection1AttestationToAttach.getId());
                attachedAttestationCollection1.add(attestationCollection1AttestationToAttach);
            }
            user.setAttestationCollection1(attachedAttestationCollection1);
            Collection<Attestation> attachedAttestationCollection2 = new ArrayList<Attestation>();
            for (Attestation attestationCollection2AttestationToAttach : user.getAttestationCollection2()) {
                attestationCollection2AttestationToAttach = em.getReference(attestationCollection2AttestationToAttach.getClass(), attestationCollection2AttestationToAttach.getId());
                attachedAttestationCollection2.add(attestationCollection2AttestationToAttach);
            }
            user.setAttestationCollection2(attachedAttestationCollection2);
            Collection<Stage> attachedStageCollection = new ArrayList<Stage>();
            for (Stage stageCollectionStageToAttach : user.getStageCollection()) {
                stageCollectionStageToAttach = em.getReference(stageCollectionStageToAttach.getClass(), stageCollectionStageToAttach.getId());
                attachedStageCollection.add(stageCollectionStageToAttach);
            }
            user.setStageCollection(attachedStageCollection);
            Collection<Stage> attachedStageCollection1 = new ArrayList<Stage>();
            for (Stage stageCollection1StageToAttach : user.getStageCollection1()) {
                stageCollection1StageToAttach = em.getReference(stageCollection1StageToAttach.getClass(), stageCollection1StageToAttach.getId());
                attachedStageCollection1.add(stageCollection1StageToAttach);
            }
            user.setStageCollection1(attachedStageCollection1);
            Collection<Demande> attachedDemandeCollection = new ArrayList<Demande>();
            for (Demande demandeCollectionDemandeToAttach : user.getDemandeCollection()) {
                demandeCollectionDemandeToAttach = em.getReference(demandeCollectionDemandeToAttach.getClass(), demandeCollectionDemandeToAttach.getId());
                attachedDemandeCollection.add(demandeCollectionDemandeToAttach);
            }
            user.setDemandeCollection(attachedDemandeCollection);
            em.persist(user);
            for (Attestation attestationCollectionAttestation : user.getAttestationCollection()) {
                User oldIdLivreurOfAttestationCollectionAttestation = attestationCollectionAttestation.getIdLivreur();
                attestationCollectionAttestation.setIdLivreur(user);
                attestationCollectionAttestation = em.merge(attestationCollectionAttestation);
                if (oldIdLivreurOfAttestationCollectionAttestation != null) {
                    oldIdLivreurOfAttestationCollectionAttestation.getAttestationCollection().remove(attestationCollectionAttestation);
                    oldIdLivreurOfAttestationCollectionAttestation = em.merge(oldIdLivreurOfAttestationCollectionAttestation);
                }
            }
            for (Attestation attestationCollection1Attestation : user.getAttestationCollection1()) {
                User oldIdSignateurOfAttestationCollection1Attestation = attestationCollection1Attestation.getIdSignateur();
                attestationCollection1Attestation.setIdSignateur(user);
                attestationCollection1Attestation = em.merge(attestationCollection1Attestation);
                if (oldIdSignateurOfAttestationCollection1Attestation != null) {
                    oldIdSignateurOfAttestationCollection1Attestation.getAttestationCollection1().remove(attestationCollection1Attestation);
                    oldIdSignateurOfAttestationCollection1Attestation = em.merge(oldIdSignateurOfAttestationCollection1Attestation);
                }
            }
            for (Attestation attestationCollection2Attestation : user.getAttestationCollection2()) {
                User oldIdStagiaireOfAttestationCollection2Attestation = attestationCollection2Attestation.getIdStagiaire();
                attestationCollection2Attestation.setIdStagiaire(user);
                attestationCollection2Attestation = em.merge(attestationCollection2Attestation);
                if (oldIdStagiaireOfAttestationCollection2Attestation != null) {
                    oldIdStagiaireOfAttestationCollection2Attestation.getAttestationCollection2().remove(attestationCollection2Attestation);
                    oldIdStagiaireOfAttestationCollection2Attestation = em.merge(oldIdStagiaireOfAttestationCollection2Attestation);
                }
            }
            for (Stage stageCollectionStage : user.getStageCollection()) {
                User oldIdEncadrantOfStageCollectionStage = stageCollectionStage.getIdEncadrant();
                stageCollectionStage.setIdEncadrant(user);
                stageCollectionStage = em.merge(stageCollectionStage);
                if (oldIdEncadrantOfStageCollectionStage != null) {
                    oldIdEncadrantOfStageCollectionStage.getStageCollection().remove(stageCollectionStage);
                    oldIdEncadrantOfStageCollectionStage = em.merge(oldIdEncadrantOfStageCollectionStage);
                }
            }
            for (Stage stageCollection1Stage : user.getStageCollection1()) {
                User oldIdStagiaireOfStageCollection1Stage = stageCollection1Stage.getIdStagiaire();
                stageCollection1Stage.setIdStagiaire(user);
                stageCollection1Stage = em.merge(stageCollection1Stage);
                if (oldIdStagiaireOfStageCollection1Stage != null) {
                    oldIdStagiaireOfStageCollection1Stage.getStageCollection1().remove(stageCollection1Stage);
                    oldIdStagiaireOfStageCollection1Stage = em.merge(oldIdStagiaireOfStageCollection1Stage);
                }
            }
            for (Demande demandeCollectionDemande : user.getDemandeCollection()) {
                User oldUserIdOfDemandeCollectionDemande = demandeCollectionDemande.getUserId();
                demandeCollectionDemande.setUserId(user);
                demandeCollectionDemande = em.merge(demandeCollectionDemande);
                if (oldUserIdOfDemandeCollectionDemande != null) {
                    oldUserIdOfDemandeCollectionDemande.getDemandeCollection().remove(demandeCollectionDemande);
                    oldUserIdOfDemandeCollectionDemande = em.merge(oldUserIdOfDemandeCollectionDemande);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findUser(user.getId()) != null) {
                throw new PreexistingEntityException("User " + user + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(User user) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            User persistentUser = em.find(User.class, user.getId());
            Collection<Attestation> attestationCollectionOld = persistentUser.getAttestationCollection();
            Collection<Attestation> attestationCollectionNew = user.getAttestationCollection();
            Collection<Attestation> attestationCollection1Old = persistentUser.getAttestationCollection1();
            Collection<Attestation> attestationCollection1New = user.getAttestationCollection1();
            Collection<Attestation> attestationCollection2Old = persistentUser.getAttestationCollection2();
            Collection<Attestation> attestationCollection2New = user.getAttestationCollection2();
            Collection<Stage> stageCollectionOld = persistentUser.getStageCollection();
            Collection<Stage> stageCollectionNew = user.getStageCollection();
            Collection<Stage> stageCollection1Old = persistentUser.getStageCollection1();
            Collection<Stage> stageCollection1New = user.getStageCollection1();
            Collection<Demande> demandeCollectionOld = persistentUser.getDemandeCollection();
            Collection<Demande> demandeCollectionNew = user.getDemandeCollection();
            List<String> illegalOrphanMessages = null;
            for (Attestation attestationCollectionOldAttestation : attestationCollectionOld) {
                if (!attestationCollectionNew.contains(attestationCollectionOldAttestation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Attestation " + attestationCollectionOldAttestation + " since its idLivreur field is not nullable.");
                }
            }
            for (Attestation attestationCollection1OldAttestation : attestationCollection1Old) {
                if (!attestationCollection1New.contains(attestationCollection1OldAttestation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Attestation " + attestationCollection1OldAttestation + " since its idSignateur field is not nullable.");
                }
            }
            for (Attestation attestationCollection2OldAttestation : attestationCollection2Old) {
                if (!attestationCollection2New.contains(attestationCollection2OldAttestation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Attestation " + attestationCollection2OldAttestation + " since its idStagiaire field is not nullable.");
                }
            }
            for (Stage stageCollectionOldStage : stageCollectionOld) {
                if (!stageCollectionNew.contains(stageCollectionOldStage)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Stage " + stageCollectionOldStage + " since its idEncadrant field is not nullable.");
                }
            }
            for (Stage stageCollection1OldStage : stageCollection1Old) {
                if (!stageCollection1New.contains(stageCollection1OldStage)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Stage " + stageCollection1OldStage + " since its idStagiaire field is not nullable.");
                }
            }
            for (Demande demandeCollectionOldDemande : demandeCollectionOld) {
                if (!demandeCollectionNew.contains(demandeCollectionOldDemande)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Demande " + demandeCollectionOldDemande + " since its userId field is not nullable.");
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
            user.setAttestationCollection(attestationCollectionNew);
            Collection<Attestation> attachedAttestationCollection1New = new ArrayList<Attestation>();
            for (Attestation attestationCollection1NewAttestationToAttach : attestationCollection1New) {
                attestationCollection1NewAttestationToAttach = em.getReference(attestationCollection1NewAttestationToAttach.getClass(), attestationCollection1NewAttestationToAttach.getId());
                attachedAttestationCollection1New.add(attestationCollection1NewAttestationToAttach);
            }
            attestationCollection1New = attachedAttestationCollection1New;
            user.setAttestationCollection1(attestationCollection1New);
            Collection<Attestation> attachedAttestationCollection2New = new ArrayList<Attestation>();
            for (Attestation attestationCollection2NewAttestationToAttach : attestationCollection2New) {
                attestationCollection2NewAttestationToAttach = em.getReference(attestationCollection2NewAttestationToAttach.getClass(), attestationCollection2NewAttestationToAttach.getId());
                attachedAttestationCollection2New.add(attestationCollection2NewAttestationToAttach);
            }
            attestationCollection2New = attachedAttestationCollection2New;
            user.setAttestationCollection2(attestationCollection2New);
            Collection<Stage> attachedStageCollectionNew = new ArrayList<Stage>();
            for (Stage stageCollectionNewStageToAttach : stageCollectionNew) {
                stageCollectionNewStageToAttach = em.getReference(stageCollectionNewStageToAttach.getClass(), stageCollectionNewStageToAttach.getId());
                attachedStageCollectionNew.add(stageCollectionNewStageToAttach);
            }
            stageCollectionNew = attachedStageCollectionNew;
            user.setStageCollection(stageCollectionNew);
            Collection<Stage> attachedStageCollection1New = new ArrayList<Stage>();
            for (Stage stageCollection1NewStageToAttach : stageCollection1New) {
                stageCollection1NewStageToAttach = em.getReference(stageCollection1NewStageToAttach.getClass(), stageCollection1NewStageToAttach.getId());
                attachedStageCollection1New.add(stageCollection1NewStageToAttach);
            }
            stageCollection1New = attachedStageCollection1New;
            user.setStageCollection1(stageCollection1New);
            Collection<Demande> attachedDemandeCollectionNew = new ArrayList<Demande>();
            for (Demande demandeCollectionNewDemandeToAttach : demandeCollectionNew) {
                demandeCollectionNewDemandeToAttach = em.getReference(demandeCollectionNewDemandeToAttach.getClass(), demandeCollectionNewDemandeToAttach.getId());
                attachedDemandeCollectionNew.add(demandeCollectionNewDemandeToAttach);
            }
            demandeCollectionNew = attachedDemandeCollectionNew;
            user.setDemandeCollection(demandeCollectionNew);
            user = em.merge(user);
            for (Attestation attestationCollectionNewAttestation : attestationCollectionNew) {
                if (!attestationCollectionOld.contains(attestationCollectionNewAttestation)) {
                    User oldIdLivreurOfAttestationCollectionNewAttestation = attestationCollectionNewAttestation.getIdLivreur();
                    attestationCollectionNewAttestation.setIdLivreur(user);
                    attestationCollectionNewAttestation = em.merge(attestationCollectionNewAttestation);
                    if (oldIdLivreurOfAttestationCollectionNewAttestation != null && !oldIdLivreurOfAttestationCollectionNewAttestation.equals(user)) {
                        oldIdLivreurOfAttestationCollectionNewAttestation.getAttestationCollection().remove(attestationCollectionNewAttestation);
                        oldIdLivreurOfAttestationCollectionNewAttestation = em.merge(oldIdLivreurOfAttestationCollectionNewAttestation);
                    }
                }
            }
            for (Attestation attestationCollection1NewAttestation : attestationCollection1New) {
                if (!attestationCollection1Old.contains(attestationCollection1NewAttestation)) {
                    User oldIdSignateurOfAttestationCollection1NewAttestation = attestationCollection1NewAttestation.getIdSignateur();
                    attestationCollection1NewAttestation.setIdSignateur(user);
                    attestationCollection1NewAttestation = em.merge(attestationCollection1NewAttestation);
                    if (oldIdSignateurOfAttestationCollection1NewAttestation != null && !oldIdSignateurOfAttestationCollection1NewAttestation.equals(user)) {
                        oldIdSignateurOfAttestationCollection1NewAttestation.getAttestationCollection1().remove(attestationCollection1NewAttestation);
                        oldIdSignateurOfAttestationCollection1NewAttestation = em.merge(oldIdSignateurOfAttestationCollection1NewAttestation);
                    }
                }
            }
            for (Attestation attestationCollection2NewAttestation : attestationCollection2New) {
                if (!attestationCollection2Old.contains(attestationCollection2NewAttestation)) {
                    User oldIdStagiaireOfAttestationCollection2NewAttestation = attestationCollection2NewAttestation.getIdStagiaire();
                    attestationCollection2NewAttestation.setIdStagiaire(user);
                    attestationCollection2NewAttestation = em.merge(attestationCollection2NewAttestation);
                    if (oldIdStagiaireOfAttestationCollection2NewAttestation != null && !oldIdStagiaireOfAttestationCollection2NewAttestation.equals(user)) {
                        oldIdStagiaireOfAttestationCollection2NewAttestation.getAttestationCollection2().remove(attestationCollection2NewAttestation);
                        oldIdStagiaireOfAttestationCollection2NewAttestation = em.merge(oldIdStagiaireOfAttestationCollection2NewAttestation);
                    }
                }
            }
            for (Stage stageCollectionNewStage : stageCollectionNew) {
                if (!stageCollectionOld.contains(stageCollectionNewStage)) {
                    User oldIdEncadrantOfStageCollectionNewStage = stageCollectionNewStage.getIdEncadrant();
                    stageCollectionNewStage.setIdEncadrant(user);
                    stageCollectionNewStage = em.merge(stageCollectionNewStage);
                    if (oldIdEncadrantOfStageCollectionNewStage != null && !oldIdEncadrantOfStageCollectionNewStage.equals(user)) {
                        oldIdEncadrantOfStageCollectionNewStage.getStageCollection().remove(stageCollectionNewStage);
                        oldIdEncadrantOfStageCollectionNewStage = em.merge(oldIdEncadrantOfStageCollectionNewStage);
                    }
                }
            }
            for (Stage stageCollection1NewStage : stageCollection1New) {
                if (!stageCollection1Old.contains(stageCollection1NewStage)) {
                    User oldIdStagiaireOfStageCollection1NewStage = stageCollection1NewStage.getIdStagiaire();
                    stageCollection1NewStage.setIdStagiaire(user);
                    stageCollection1NewStage = em.merge(stageCollection1NewStage);
                    if (oldIdStagiaireOfStageCollection1NewStage != null && !oldIdStagiaireOfStageCollection1NewStage.equals(user)) {
                        oldIdStagiaireOfStageCollection1NewStage.getStageCollection1().remove(stageCollection1NewStage);
                        oldIdStagiaireOfStageCollection1NewStage = em.merge(oldIdStagiaireOfStageCollection1NewStage);
                    }
                }
            }
            for (Demande demandeCollectionNewDemande : demandeCollectionNew) {
                if (!demandeCollectionOld.contains(demandeCollectionNewDemande)) {
                    User oldUserIdOfDemandeCollectionNewDemande = demandeCollectionNewDemande.getUserId();
                    demandeCollectionNewDemande.setUserId(user);
                    demandeCollectionNewDemande = em.merge(demandeCollectionNewDemande);
                    if (oldUserIdOfDemandeCollectionNewDemande != null && !oldUserIdOfDemandeCollectionNewDemande.equals(user)) {
                        oldUserIdOfDemandeCollectionNewDemande.getDemandeCollection().remove(demandeCollectionNewDemande);
                        oldUserIdOfDemandeCollectionNewDemande = em.merge(oldUserIdOfDemandeCollectionNewDemande);
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
                Integer id = user.getId();
                if (findUser(id) == null) {
                    throw new NonexistentEntityException("The user with id " + id + " no longer exists.");
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
            User user;
            try {
                user = em.getReference(User.class, id);
                user.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The user with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Attestation> attestationCollectionOrphanCheck = user.getAttestationCollection();
            for (Attestation attestationCollectionOrphanCheckAttestation : attestationCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Attestation " + attestationCollectionOrphanCheckAttestation + " in its attestationCollection field has a non-nullable idLivreur field.");
            }
            Collection<Attestation> attestationCollection1OrphanCheck = user.getAttestationCollection1();
            for (Attestation attestationCollection1OrphanCheckAttestation : attestationCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Attestation " + attestationCollection1OrphanCheckAttestation + " in its attestationCollection1 field has a non-nullable idSignateur field.");
            }
            Collection<Attestation> attestationCollection2OrphanCheck = user.getAttestationCollection2();
            for (Attestation attestationCollection2OrphanCheckAttestation : attestationCollection2OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Attestation " + attestationCollection2OrphanCheckAttestation + " in its attestationCollection2 field has a non-nullable idStagiaire field.");
            }
            Collection<Stage> stageCollectionOrphanCheck = user.getStageCollection();
            for (Stage stageCollectionOrphanCheckStage : stageCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Stage " + stageCollectionOrphanCheckStage + " in its stageCollection field has a non-nullable idEncadrant field.");
            }
            Collection<Stage> stageCollection1OrphanCheck = user.getStageCollection1();
            for (Stage stageCollection1OrphanCheckStage : stageCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Stage " + stageCollection1OrphanCheckStage + " in its stageCollection1 field has a non-nullable idStagiaire field.");
            }
            Collection<Demande> demandeCollectionOrphanCheck = user.getDemandeCollection();
            for (Demande demandeCollectionOrphanCheckDemande : demandeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Demande " + demandeCollectionOrphanCheckDemande + " in its demandeCollection field has a non-nullable userId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(user);
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

    public List<User> findUserEntities() {
        return findUserEntities(true, -1, -1);
    }

    public List<User> findUserEntities(int maxResults, int firstResult) {
        return findUserEntities(false, maxResults, firstResult);
    }

    private List<User> findUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(User.class));
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

    public User findUser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<User> rt = cq.from(User.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
