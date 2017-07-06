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
import entitie.Fonction;
import entitie.Attestation;
import java.util.ArrayList;
import java.util.List;
import entitie.Stage;
import entitie.Demande;
import entitie.User;
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
        if (user.getAttestationList() == null) {
            user.setAttestationList(new ArrayList<Attestation>());
        }
        if (user.getAttestationList1() == null) {
            user.setAttestationList1(new ArrayList<Attestation>());
        }
        if (user.getAttestationList2() == null) {
            user.setAttestationList2(new ArrayList<Attestation>());
        }
        if (user.getStageList() == null) {
            user.setStageList(new ArrayList<Stage>());
        }
        if (user.getStageList1() == null) {
            user.setStageList1(new ArrayList<Stage>());
        }
        if (user.getDemandeList() == null) {
            user.setDemandeList(new ArrayList<Demande>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Fonction fonction = user.getFonction();
            if (fonction != null) {
                fonction = em.getReference(fonction.getClass(), fonction.getId());
                user.setFonction(fonction);
            }
            List<Attestation> attachedAttestationList = new ArrayList<Attestation>();
            for (Attestation attestationListAttestationToAttach : user.getAttestationList()) {
                attestationListAttestationToAttach = em.getReference(attestationListAttestationToAttach.getClass(), attestationListAttestationToAttach.getId());
                attachedAttestationList.add(attestationListAttestationToAttach);
            }
            user.setAttestationList(attachedAttestationList);
            List<Attestation> attachedAttestationList1 = new ArrayList<Attestation>();
            for (Attestation attestationList1AttestationToAttach : user.getAttestationList1()) {
                attestationList1AttestationToAttach = em.getReference(attestationList1AttestationToAttach.getClass(), attestationList1AttestationToAttach.getId());
                attachedAttestationList1.add(attestationList1AttestationToAttach);
            }
            user.setAttestationList1(attachedAttestationList1);
            List<Attestation> attachedAttestationList2 = new ArrayList<Attestation>();
            for (Attestation attestationList2AttestationToAttach : user.getAttestationList2()) {
                attestationList2AttestationToAttach = em.getReference(attestationList2AttestationToAttach.getClass(), attestationList2AttestationToAttach.getId());
                attachedAttestationList2.add(attestationList2AttestationToAttach);
            }
            user.setAttestationList2(attachedAttestationList2);
            List<Stage> attachedStageList = new ArrayList<Stage>();
            for (Stage stageListStageToAttach : user.getStageList()) {
                stageListStageToAttach = em.getReference(stageListStageToAttach.getClass(), stageListStageToAttach.getId());
                attachedStageList.add(stageListStageToAttach);
            }
            user.setStageList(attachedStageList);
            List<Stage> attachedStageList1 = new ArrayList<Stage>();
            for (Stage stageList1StageToAttach : user.getStageList1()) {
                stageList1StageToAttach = em.getReference(stageList1StageToAttach.getClass(), stageList1StageToAttach.getId());
                attachedStageList1.add(stageList1StageToAttach);
            }
            user.setStageList1(attachedStageList1);
            List<Demande> attachedDemandeList = new ArrayList<Demande>();
            for (Demande demandeListDemandeToAttach : user.getDemandeList()) {
                demandeListDemandeToAttach = em.getReference(demandeListDemandeToAttach.getClass(), demandeListDemandeToAttach.getId());
                attachedDemandeList.add(demandeListDemandeToAttach);
            }
            user.setDemandeList(attachedDemandeList);
            em.persist(user);
            if (fonction != null) {
                fonction.getUserList().add(user);
                fonction = em.merge(fonction);
            }
            for (Attestation attestationListAttestation : user.getAttestationList()) {
                User oldIdLivreurOfAttestationListAttestation = attestationListAttestation.getIdLivreur();
                attestationListAttestation.setIdLivreur(user);
                attestationListAttestation = em.merge(attestationListAttestation);
                if (oldIdLivreurOfAttestationListAttestation != null) {
                    oldIdLivreurOfAttestationListAttestation.getAttestationList().remove(attestationListAttestation);
                    oldIdLivreurOfAttestationListAttestation = em.merge(oldIdLivreurOfAttestationListAttestation);
                }
            }
            for (Attestation attestationList1Attestation : user.getAttestationList1()) {
                User oldIdSignateurOfAttestationList1Attestation = attestationList1Attestation.getIdSignateur();
                attestationList1Attestation.setIdSignateur(user);
                attestationList1Attestation = em.merge(attestationList1Attestation);
                if (oldIdSignateurOfAttestationList1Attestation != null) {
                    oldIdSignateurOfAttestationList1Attestation.getAttestationList1().remove(attestationList1Attestation);
                    oldIdSignateurOfAttestationList1Attestation = em.merge(oldIdSignateurOfAttestationList1Attestation);
                }
            }
            for (Attestation attestationList2Attestation : user.getAttestationList2()) {
                User oldIdStagiaireOfAttestationList2Attestation = attestationList2Attestation.getIdStagiaire();
                attestationList2Attestation.setIdStagiaire(user);
                attestationList2Attestation = em.merge(attestationList2Attestation);
                if (oldIdStagiaireOfAttestationList2Attestation != null) {
                    oldIdStagiaireOfAttestationList2Attestation.getAttestationList2().remove(attestationList2Attestation);
                    oldIdStagiaireOfAttestationList2Attestation = em.merge(oldIdStagiaireOfAttestationList2Attestation);
                }
            }
            for (Stage stageListStage : user.getStageList()) {
                User oldIdEncadrantOfStageListStage = stageListStage.getIdEncadrant();
                stageListStage.setIdEncadrant(user);
                stageListStage = em.merge(stageListStage);
                if (oldIdEncadrantOfStageListStage != null) {
                    oldIdEncadrantOfStageListStage.getStageList().remove(stageListStage);
                    oldIdEncadrantOfStageListStage = em.merge(oldIdEncadrantOfStageListStage);
                }
            }
            for (Stage stageList1Stage : user.getStageList1()) {
                User oldIdStagiaireOfStageList1Stage = stageList1Stage.getIdStagiaire();
                stageList1Stage.setIdStagiaire(user);
                stageList1Stage = em.merge(stageList1Stage);
                if (oldIdStagiaireOfStageList1Stage != null) {
                    oldIdStagiaireOfStageList1Stage.getStageList1().remove(stageList1Stage);
                    oldIdStagiaireOfStageList1Stage = em.merge(oldIdStagiaireOfStageList1Stage);
                }
            }
            for (Demande demandeListDemande : user.getDemandeList()) {
                User oldUserIdOfDemandeListDemande = demandeListDemande.getUserId();
                demandeListDemande.setUserId(user);
                demandeListDemande = em.merge(demandeListDemande);
                if (oldUserIdOfDemandeListDemande != null) {
                    oldUserIdOfDemandeListDemande.getDemandeList().remove(demandeListDemande);
                    oldUserIdOfDemandeListDemande = em.merge(oldUserIdOfDemandeListDemande);
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
            Fonction fonctionOld = persistentUser.getFonction();
            Fonction fonctionNew = user.getFonction();
            List<Attestation> attestationListOld = persistentUser.getAttestationList();
            List<Attestation> attestationListNew = user.getAttestationList();
            List<Attestation> attestationList1Old = persistentUser.getAttestationList1();
            List<Attestation> attestationList1New = user.getAttestationList1();
            List<Attestation> attestationList2Old = persistentUser.getAttestationList2();
            List<Attestation> attestationList2New = user.getAttestationList2();
            List<Stage> stageListOld = persistentUser.getStageList();
            List<Stage> stageListNew = user.getStageList();
            List<Stage> stageList1Old = persistentUser.getStageList1();
            List<Stage> stageList1New = user.getStageList1();
            List<Demande> demandeListOld = persistentUser.getDemandeList();
            List<Demande> demandeListNew = user.getDemandeList();
            List<String> illegalOrphanMessages = null;
            for (Attestation attestationListOldAttestation : attestationListOld) {
                if (!attestationListNew.contains(attestationListOldAttestation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Attestation " + attestationListOldAttestation + " since its idLivreur field is not nullable.");
                }
            }
            for (Attestation attestationList1OldAttestation : attestationList1Old) {
                if (!attestationList1New.contains(attestationList1OldAttestation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Attestation " + attestationList1OldAttestation + " since its idSignateur field is not nullable.");
                }
            }
            for (Attestation attestationList2OldAttestation : attestationList2Old) {
                if (!attestationList2New.contains(attestationList2OldAttestation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Attestation " + attestationList2OldAttestation + " since its idStagiaire field is not nullable.");
                }
            }
            for (Stage stageListOldStage : stageListOld) {
                if (!stageListNew.contains(stageListOldStage)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Stage " + stageListOldStage + " since its idEncadrant field is not nullable.");
                }
            }
            for (Stage stageList1OldStage : stageList1Old) {
                if (!stageList1New.contains(stageList1OldStage)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Stage " + stageList1OldStage + " since its idStagiaire field is not nullable.");
                }
            }
            for (Demande demandeListOldDemande : demandeListOld) {
                if (!demandeListNew.contains(demandeListOldDemande)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Demande " + demandeListOldDemande + " since its userId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (fonctionNew != null) {
                fonctionNew = em.getReference(fonctionNew.getClass(), fonctionNew.getId());
                user.setFonction(fonctionNew);
            }
            List<Attestation> attachedAttestationListNew = new ArrayList<Attestation>();
            for (Attestation attestationListNewAttestationToAttach : attestationListNew) {
                attestationListNewAttestationToAttach = em.getReference(attestationListNewAttestationToAttach.getClass(), attestationListNewAttestationToAttach.getId());
                attachedAttestationListNew.add(attestationListNewAttestationToAttach);
            }
            attestationListNew = attachedAttestationListNew;
            user.setAttestationList(attestationListNew);
            List<Attestation> attachedAttestationList1New = new ArrayList<Attestation>();
            for (Attestation attestationList1NewAttestationToAttach : attestationList1New) {
                attestationList1NewAttestationToAttach = em.getReference(attestationList1NewAttestationToAttach.getClass(), attestationList1NewAttestationToAttach.getId());
                attachedAttestationList1New.add(attestationList1NewAttestationToAttach);
            }
            attestationList1New = attachedAttestationList1New;
            user.setAttestationList1(attestationList1New);
            List<Attestation> attachedAttestationList2New = new ArrayList<Attestation>();
            for (Attestation attestationList2NewAttestationToAttach : attestationList2New) {
                attestationList2NewAttestationToAttach = em.getReference(attestationList2NewAttestationToAttach.getClass(), attestationList2NewAttestationToAttach.getId());
                attachedAttestationList2New.add(attestationList2NewAttestationToAttach);
            }
            attestationList2New = attachedAttestationList2New;
            user.setAttestationList2(attestationList2New);
            List<Stage> attachedStageListNew = new ArrayList<Stage>();
            for (Stage stageListNewStageToAttach : stageListNew) {
                stageListNewStageToAttach = em.getReference(stageListNewStageToAttach.getClass(), stageListNewStageToAttach.getId());
                attachedStageListNew.add(stageListNewStageToAttach);
            }
            stageListNew = attachedStageListNew;
            user.setStageList(stageListNew);
            List<Stage> attachedStageList1New = new ArrayList<Stage>();
            for (Stage stageList1NewStageToAttach : stageList1New) {
                stageList1NewStageToAttach = em.getReference(stageList1NewStageToAttach.getClass(), stageList1NewStageToAttach.getId());
                attachedStageList1New.add(stageList1NewStageToAttach);
            }
            stageList1New = attachedStageList1New;
            user.setStageList1(stageList1New);
            List<Demande> attachedDemandeListNew = new ArrayList<Demande>();
            for (Demande demandeListNewDemandeToAttach : demandeListNew) {
                demandeListNewDemandeToAttach = em.getReference(demandeListNewDemandeToAttach.getClass(), demandeListNewDemandeToAttach.getId());
                attachedDemandeListNew.add(demandeListNewDemandeToAttach);
            }
            demandeListNew = attachedDemandeListNew;
            user.setDemandeList(demandeListNew);
            user = em.merge(user);
            if (fonctionOld != null && !fonctionOld.equals(fonctionNew)) {
                fonctionOld.getUserList().remove(user);
                fonctionOld = em.merge(fonctionOld);
            }
            if (fonctionNew != null && !fonctionNew.equals(fonctionOld)) {
                fonctionNew.getUserList().add(user);
                fonctionNew = em.merge(fonctionNew);
            }
            for (Attestation attestationListNewAttestation : attestationListNew) {
                if (!attestationListOld.contains(attestationListNewAttestation)) {
                    User oldIdLivreurOfAttestationListNewAttestation = attestationListNewAttestation.getIdLivreur();
                    attestationListNewAttestation.setIdLivreur(user);
                    attestationListNewAttestation = em.merge(attestationListNewAttestation);
                    if (oldIdLivreurOfAttestationListNewAttestation != null && !oldIdLivreurOfAttestationListNewAttestation.equals(user)) {
                        oldIdLivreurOfAttestationListNewAttestation.getAttestationList().remove(attestationListNewAttestation);
                        oldIdLivreurOfAttestationListNewAttestation = em.merge(oldIdLivreurOfAttestationListNewAttestation);
                    }
                }
            }
            for (Attestation attestationList1NewAttestation : attestationList1New) {
                if (!attestationList1Old.contains(attestationList1NewAttestation)) {
                    User oldIdSignateurOfAttestationList1NewAttestation = attestationList1NewAttestation.getIdSignateur();
                    attestationList1NewAttestation.setIdSignateur(user);
                    attestationList1NewAttestation = em.merge(attestationList1NewAttestation);
                    if (oldIdSignateurOfAttestationList1NewAttestation != null && !oldIdSignateurOfAttestationList1NewAttestation.equals(user)) {
                        oldIdSignateurOfAttestationList1NewAttestation.getAttestationList1().remove(attestationList1NewAttestation);
                        oldIdSignateurOfAttestationList1NewAttestation = em.merge(oldIdSignateurOfAttestationList1NewAttestation);
                    }
                }
            }
            for (Attestation attestationList2NewAttestation : attestationList2New) {
                if (!attestationList2Old.contains(attestationList2NewAttestation)) {
                    User oldIdStagiaireOfAttestationList2NewAttestation = attestationList2NewAttestation.getIdStagiaire();
                    attestationList2NewAttestation.setIdStagiaire(user);
                    attestationList2NewAttestation = em.merge(attestationList2NewAttestation);
                    if (oldIdStagiaireOfAttestationList2NewAttestation != null && !oldIdStagiaireOfAttestationList2NewAttestation.equals(user)) {
                        oldIdStagiaireOfAttestationList2NewAttestation.getAttestationList2().remove(attestationList2NewAttestation);
                        oldIdStagiaireOfAttestationList2NewAttestation = em.merge(oldIdStagiaireOfAttestationList2NewAttestation);
                    }
                }
            }
            for (Stage stageListNewStage : stageListNew) {
                if (!stageListOld.contains(stageListNewStage)) {
                    User oldIdEncadrantOfStageListNewStage = stageListNewStage.getIdEncadrant();
                    stageListNewStage.setIdEncadrant(user);
                    stageListNewStage = em.merge(stageListNewStage);
                    if (oldIdEncadrantOfStageListNewStage != null && !oldIdEncadrantOfStageListNewStage.equals(user)) {
                        oldIdEncadrantOfStageListNewStage.getStageList().remove(stageListNewStage);
                        oldIdEncadrantOfStageListNewStage = em.merge(oldIdEncadrantOfStageListNewStage);
                    }
                }
            }
            for (Stage stageList1NewStage : stageList1New) {
                if (!stageList1Old.contains(stageList1NewStage)) {
                    User oldIdStagiaireOfStageList1NewStage = stageList1NewStage.getIdStagiaire();
                    stageList1NewStage.setIdStagiaire(user);
                    stageList1NewStage = em.merge(stageList1NewStage);
                    if (oldIdStagiaireOfStageList1NewStage != null && !oldIdStagiaireOfStageList1NewStage.equals(user)) {
                        oldIdStagiaireOfStageList1NewStage.getStageList1().remove(stageList1NewStage);
                        oldIdStagiaireOfStageList1NewStage = em.merge(oldIdStagiaireOfStageList1NewStage);
                    }
                }
            }
            for (Demande demandeListNewDemande : demandeListNew) {
                if (!demandeListOld.contains(demandeListNewDemande)) {
                    User oldUserIdOfDemandeListNewDemande = demandeListNewDemande.getUserId();
                    demandeListNewDemande.setUserId(user);
                    demandeListNewDemande = em.merge(demandeListNewDemande);
                    if (oldUserIdOfDemandeListNewDemande != null && !oldUserIdOfDemandeListNewDemande.equals(user)) {
                        oldUserIdOfDemandeListNewDemande.getDemandeList().remove(demandeListNewDemande);
                        oldUserIdOfDemandeListNewDemande = em.merge(oldUserIdOfDemandeListNewDemande);
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
            List<Attestation> attestationListOrphanCheck = user.getAttestationList();
            for (Attestation attestationListOrphanCheckAttestation : attestationListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Attestation " + attestationListOrphanCheckAttestation + " in its attestationList field has a non-nullable idLivreur field.");
            }
            List<Attestation> attestationList1OrphanCheck = user.getAttestationList1();
            for (Attestation attestationList1OrphanCheckAttestation : attestationList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Attestation " + attestationList1OrphanCheckAttestation + " in its attestationList1 field has a non-nullable idSignateur field.");
            }
            List<Attestation> attestationList2OrphanCheck = user.getAttestationList2();
            for (Attestation attestationList2OrphanCheckAttestation : attestationList2OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Attestation " + attestationList2OrphanCheckAttestation + " in its attestationList2 field has a non-nullable idStagiaire field.");
            }
            List<Stage> stageListOrphanCheck = user.getStageList();
            for (Stage stageListOrphanCheckStage : stageListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Stage " + stageListOrphanCheckStage + " in its stageList field has a non-nullable idEncadrant field.");
            }
            List<Stage> stageList1OrphanCheck = user.getStageList1();
            for (Stage stageList1OrphanCheckStage : stageList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Stage " + stageList1OrphanCheckStage + " in its stageList1 field has a non-nullable idStagiaire field.");
            }
            List<Demande> demandeListOrphanCheck = user.getDemandeList();
            for (Demande demandeListOrphanCheckDemande : demandeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Demande " + demandeListOrphanCheckDemande + " in its demandeList field has a non-nullable userId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Fonction fonction = user.getFonction();
            if (fonction != null) {
                fonction.getUserList().remove(user);
                fonction = em.merge(fonction);
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
