/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import entitie.Attestation;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entitie.Stage;
import entitie.User;
import entitie.Statusattestation;
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
public class AttestationJpaController implements Serializable {

    public AttestationJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Attestation attestation) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Stage idStage = attestation.getIdStage();
            if (idStage != null) {
                idStage = em.getReference(idStage.getClass(), idStage.getId());
                attestation.setIdStage(idStage);
            }
            User idLivreur = attestation.getIdLivreur();
            if (idLivreur != null) {
                idLivreur = em.getReference(idLivreur.getClass(), idLivreur.getId());
                attestation.setIdLivreur(idLivreur);
            }
            User idSignateur = attestation.getIdSignateur();
            if (idSignateur != null) {
                idSignateur = em.getReference(idSignateur.getClass(), idSignateur.getId());
                attestation.setIdSignateur(idSignateur);
            }
            User idStagiaire = attestation.getIdStagiaire();
            if (idStagiaire != null) {
                idStagiaire = em.getReference(idStagiaire.getClass(), idStagiaire.getId());
                attestation.setIdStagiaire(idStagiaire);
            }
            Statusattestation idStatut = attestation.getIdStatut();
            if (idStatut != null) {
                idStatut = em.getReference(idStatut.getClass(), idStatut.getId());
                attestation.setIdStatut(idStatut);
            }
            em.persist(attestation);
            if (idStage != null) {
                idStage.getAttestationList().add(attestation);
                idStage = em.merge(idStage);
            }
            if (idLivreur != null) {
                idLivreur.getAttestationList().add(attestation);
                idLivreur = em.merge(idLivreur);
            }
            if (idSignateur != null) {
                idSignateur.getAttestationList().add(attestation);
                idSignateur = em.merge(idSignateur);
            }
            if (idStagiaire != null) {
                idStagiaire.getAttestationList().add(attestation);
                idStagiaire = em.merge(idStagiaire);
            }
            if (idStatut != null) {
                idStatut.getAttestationList().add(attestation);
                idStatut = em.merge(idStatut);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findAttestation(attestation.getId()) != null) {
                throw new PreexistingEntityException("Attestation " + attestation + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Attestation attestation) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Attestation persistentAttestation = em.find(Attestation.class, attestation.getId());
            Stage idStageOld = persistentAttestation.getIdStage();
            Stage idStageNew = attestation.getIdStage();
            User idLivreurOld = persistentAttestation.getIdLivreur();
            User idLivreurNew = attestation.getIdLivreur();
            User idSignateurOld = persistentAttestation.getIdSignateur();
            User idSignateurNew = attestation.getIdSignateur();
            User idStagiaireOld = persistentAttestation.getIdStagiaire();
            User idStagiaireNew = attestation.getIdStagiaire();
            Statusattestation idStatutOld = persistentAttestation.getIdStatut();
            Statusattestation idStatutNew = attestation.getIdStatut();
            if (idStageNew != null) {
                idStageNew = em.getReference(idStageNew.getClass(), idStageNew.getId());
                attestation.setIdStage(idStageNew);
            }
            if (idLivreurNew != null) {
                idLivreurNew = em.getReference(idLivreurNew.getClass(), idLivreurNew.getId());
                attestation.setIdLivreur(idLivreurNew);
            }
            if (idSignateurNew != null) {
                idSignateurNew = em.getReference(idSignateurNew.getClass(), idSignateurNew.getId());
                attestation.setIdSignateur(idSignateurNew);
            }
            if (idStagiaireNew != null) {
                idStagiaireNew = em.getReference(idStagiaireNew.getClass(), idStagiaireNew.getId());
                attestation.setIdStagiaire(idStagiaireNew);
            }
            if (idStatutNew != null) {
                idStatutNew = em.getReference(idStatutNew.getClass(), idStatutNew.getId());
                attestation.setIdStatut(idStatutNew);
            }
            attestation = em.merge(attestation);
            if (idStageOld != null && !idStageOld.equals(idStageNew)) {
                idStageOld.getAttestationList().remove(attestation);
                idStageOld = em.merge(idStageOld);
            }
            if (idStageNew != null && !idStageNew.equals(idStageOld)) {
                idStageNew.getAttestationList().add(attestation);
                idStageNew = em.merge(idStageNew);
            }
            if (idLivreurOld != null && !idLivreurOld.equals(idLivreurNew)) {
                idLivreurOld.getAttestationList().remove(attestation);
                idLivreurOld = em.merge(idLivreurOld);
            }
            if (idLivreurNew != null && !idLivreurNew.equals(idLivreurOld)) {
                idLivreurNew.getAttestationList().add(attestation);
                idLivreurNew = em.merge(idLivreurNew);
            }
            if (idSignateurOld != null && !idSignateurOld.equals(idSignateurNew)) {
                idSignateurOld.getAttestationList().remove(attestation);
                idSignateurOld = em.merge(idSignateurOld);
            }
            if (idSignateurNew != null && !idSignateurNew.equals(idSignateurOld)) {
                idSignateurNew.getAttestationList().add(attestation);
                idSignateurNew = em.merge(idSignateurNew);
            }
            if (idStagiaireOld != null && !idStagiaireOld.equals(idStagiaireNew)) {
                idStagiaireOld.getAttestationList().remove(attestation);
                idStagiaireOld = em.merge(idStagiaireOld);
            }
            if (idStagiaireNew != null && !idStagiaireNew.equals(idStagiaireOld)) {
                idStagiaireNew.getAttestationList().add(attestation);
                idStagiaireNew = em.merge(idStagiaireNew);
            }
            if (idStatutOld != null && !idStatutOld.equals(idStatutNew)) {
                idStatutOld.getAttestationList().remove(attestation);
                idStatutOld = em.merge(idStatutOld);
            }
            if (idStatutNew != null && !idStatutNew.equals(idStatutOld)) {
                idStatutNew.getAttestationList().add(attestation);
                idStatutNew = em.merge(idStatutNew);
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
                Integer id = attestation.getId();
                if (findAttestation(id) == null) {
                    throw new NonexistentEntityException("The attestation with id " + id + " no longer exists.");
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
            Attestation attestation;
            try {
                attestation = em.getReference(Attestation.class, id);
                attestation.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The attestation with id " + id + " no longer exists.", enfe);
            }
            Stage idStage = attestation.getIdStage();
            if (idStage != null) {
                idStage.getAttestationList().remove(attestation);
                idStage = em.merge(idStage);
            }
            User idLivreur = attestation.getIdLivreur();
            if (idLivreur != null) {
                idLivreur.getAttestationList().remove(attestation);
                idLivreur = em.merge(idLivreur);
            }
            User idSignateur = attestation.getIdSignateur();
            if (idSignateur != null) {
                idSignateur.getAttestationList().remove(attestation);
                idSignateur = em.merge(idSignateur);
            }
            User idStagiaire = attestation.getIdStagiaire();
            if (idStagiaire != null) {
                idStagiaire.getAttestationList().remove(attestation);
                idStagiaire = em.merge(idStagiaire);
            }
            Statusattestation idStatut = attestation.getIdStatut();
            if (idStatut != null) {
                idStatut.getAttestationList().remove(attestation);
                idStatut = em.merge(idStatut);
            }
            em.remove(attestation);
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

    public List<Attestation> findAttestationEntities() {
        return findAttestationEntities(true, -1, -1);
    }

    public List<Attestation> findAttestationEntities(int maxResults, int firstResult) {
        return findAttestationEntities(false, maxResults, firstResult);
    }

    private List<Attestation> findAttestationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Attestation.class));
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

    public Attestation findAttestation(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Attestation.class, id);
        } finally {
            em.close();
        }
    }

    public int getAttestationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Attestation> rt = cq.from(Attestation.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
