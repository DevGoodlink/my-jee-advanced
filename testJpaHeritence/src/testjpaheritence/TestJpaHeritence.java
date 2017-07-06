/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjpaheritence;

import entities.Voiture;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import metier.Vehicule;

/**
 *
 * @author YASSALIE
 */
public class TestJpaHeritence {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        System.out.println("Début");
        Vehicule v=new Voiture();
        v.setMarque("Fiat");
        v.setMatricule("1111-a-1");
        persist(v);
       System.out.println("Fin");
        
        
        
    }

    public static void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testJpaHeritencePU");
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
