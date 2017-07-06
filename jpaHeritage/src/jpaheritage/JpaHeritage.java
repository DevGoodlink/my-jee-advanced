/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaheritage;

import entities.Camion;
import entities.Vehicule;
import entities.Voiture;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author YASSALIE
 */
public class JpaHeritage {

    public static void main(String[] args) {
       System.out.println("Début");
       Camion c = new Camion();
       c.setMarque("Volvo");
       c.setCouleur("Blanc");
       c.setPtc(20000);
       c.setType("Remorque");
       persist(c);
       System.out.println("Fin-----------");
    }

    public static void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaHeritagePU");
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
