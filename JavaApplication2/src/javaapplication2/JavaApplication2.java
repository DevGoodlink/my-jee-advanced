/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import entitylayer.Book;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author YASSALIE
 */
public class JavaApplication2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaApplication2PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        Book b = new Book();
        b.setTitle("JEE");
        et.begin();
        em.persist(b);
        et.commit();
        
        Book b0= em.find(Book.class, 1l);
        System.out.println(b0.getTitle());
        
        
    }
    
}
