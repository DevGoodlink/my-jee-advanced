/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entitylayer.Book;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author YASSALIE
 */
public class MainTest {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testjpaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        
        Book b = new Book();
        b.setTitle("JEE");
        
        t.begin();
        em.persist(b);
        t.commit();
        
    }
}
