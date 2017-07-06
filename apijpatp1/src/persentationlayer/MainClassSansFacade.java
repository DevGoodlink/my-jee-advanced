/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persentationlayer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import persistancelayer.entity.Etudiant;

/**
 *
 * @author YASSALIE
 */
public class MainClassSansFacade {

    public static void main(String[] args) {
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("apijpatp1PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Etudiant etu = new Etudiant();
//        etu.setNom("ALAOUI");
//        etu.setPrenom("Mohamed");
//        etu.setVille("Fes");

//        tx.begin();
//        em.persist(etu);
//        tx.commit();
        etu=em.find(Etudiant.class, 1L);
        System.out.println(etu.toString());
        
        tx.begin();
        em.remove(etu);
        tx.commit();
        
        em.close();
        emf.close();
       
    }
    
}
