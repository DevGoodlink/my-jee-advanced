/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaheritage2;

import entities.Employee;
import entities.Engineer;
import entities.Technical;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author YASSALIE
 */
public class JpaHeritage2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Début");
        Technical e = new Technical();
        e.setFirstName("Ali");
        e.setLastName("sbai");
        e.setNiveau("Débutant");
        e.setPoste("Executant");
        persist(e);
        System.out.println("Fin");
    }

    public static void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaHeritage2PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}
