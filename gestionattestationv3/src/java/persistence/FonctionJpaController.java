/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import entitie.Fonction;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entitie.User;
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
public class FonctionJpaController implements Serializable {

    public FonctionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Fonction fonction) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (fonction.getUserList() == null) {
            fonction.setUserList(new ArrayList<User>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<User> attachedUserList = new ArrayList<User>();
            for (User userListUserToAttach : fonction.getUserList()) {
                userListUserToAttach = em.getReference(userListUserToAttach.getClass(), userListUserToAttach.getId());
                attachedUserList.add(userListUserToAttach);
            }
            fonction.setUserList(attachedUserList);
            em.persist(fonction);
            for (User userListUser : fonction.getUserList()) {
                Fonction oldFonctionOfUserListUser = userListUser.getFonction();
                userListUser.setFonction(fonction);
                userListUser = em.merge(userListUser);
                if (oldFonctionOfUserListUser != null) {
                    oldFonctionOfUserListUser.getUserList().remove(userListUser);
                    oldFonctionOfUserListUser = em.merge(oldFonctionOfUserListUser);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findFonction(fonction.getId()) != null) {
                throw new PreexistingEntityException("Fonction " + fonction + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Fonction fonction) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Fonction persistentFonction = em.find(Fonction.class, fonction.getId());
            List<User> userListOld = persistentFonction.getUserList();
            List<User> userListNew = fonction.getUserList();
            List<User> attachedUserListNew = new ArrayList<User>();
            for (User userListNewUserToAttach : userListNew) {
                userListNewUserToAttach = em.getReference(userListNewUserToAttach.getClass(), userListNewUserToAttach.getId());
                attachedUserListNew.add(userListNewUserToAttach);
            }
            userListNew = attachedUserListNew;
            fonction.setUserList(userListNew);
            fonction = em.merge(fonction);
            for (User userListOldUser : userListOld) {
                if (!userListNew.contains(userListOldUser)) {
                    userListOldUser.setFonction(null);
                    userListOldUser = em.merge(userListOldUser);
                }
            }
            for (User userListNewUser : userListNew) {
                if (!userListOld.contains(userListNewUser)) {
                    Fonction oldFonctionOfUserListNewUser = userListNewUser.getFonction();
                    userListNewUser.setFonction(fonction);
                    userListNewUser = em.merge(userListNewUser);
                    if (oldFonctionOfUserListNewUser != null && !oldFonctionOfUserListNewUser.equals(fonction)) {
                        oldFonctionOfUserListNewUser.getUserList().remove(userListNewUser);
                        oldFonctionOfUserListNewUser = em.merge(oldFonctionOfUserListNewUser);
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
                Integer id = fonction.getId();
                if (findFonction(id) == null) {
                    throw new NonexistentEntityException("The fonction with id " + id + " no longer exists.");
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
            Fonction fonction;
            try {
                fonction = em.getReference(Fonction.class, id);
                fonction.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fonction with id " + id + " no longer exists.", enfe);
            }
            List<User> userList = fonction.getUserList();
            for (User userListUser : userList) {
                userListUser.setFonction(null);
                userListUser = em.merge(userListUser);
            }
            em.remove(fonction);
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

    public List<Fonction> findFonctionEntities() {
        return findFonctionEntities(true, -1, -1);
    }

    public List<Fonction> findFonctionEntities(int maxResults, int firstResult) {
        return findFonctionEntities(false, maxResults, firstResult);
    }

    private List<Fonction> findFonctionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Fonction.class));
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

    public Fonction findFonction(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fonction.class, id);
        } finally {
            em.close();
        }
    }

    public int getFonctionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Fonction> rt = cq.from(Fonction.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
