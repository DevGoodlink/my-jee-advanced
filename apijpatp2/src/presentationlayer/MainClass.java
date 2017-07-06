/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentationlayer;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import persistencelayer.Adress;
import persistencelayer.Customer;

/**
 *
 * @author YASSALIE
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("apijpatp2PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        Customer c = new Customer();
        c.setFirstName("Mohamed");
        c.setLastName("Mansouri");
        c.setEmail("alimansouri@gmail.com");
        
        Customer c1 = new Customer();
        c1.setFirstName("Ali");
        c1.setLastName("Mansouri");
        c1.setEmail("alimansouri@gmail.com");
        
        Customer c2 = new Customer();
        c2.setFirstName("Anouar");
        c2.setLastName("Mansouri");
        c2.setEmail("alimansouri@gmail.com");
        
        Adress a = new Adress();
        a.setStreet1("Ain haroun");
        a.setCountry("Maroc");
        a.setCity("Fes");
        a.setZipCode("3000");
        //c.setAdress(a);
        
        try {
            /*tx.begin();
            em.persist(c);
            em.persist(c1);
            em.persist(c2);
            tx.commit();
            Query query= em.createQuery("Select c from Customer c");
            List<Customer> customers = query.getResultList();
            for(Customer cc : customers) System.out.println(cc.getId() + "  - "+cc.getFirstName());
            //c=em.find(Customer.class, 2L);
            //em.remove(c);
            */
//            Query query = em.createQuery("Select c from Customer c where c.firstName=:fname");
//            query.setParameter("fname", "Ali");
            //Query query = em.createNamedQuery("findAll");
            //query.setMaxResults(2);
            /*Query query = em.createNamedQuery("findByFirstName");
            query.setParameter("fname", "Ali");
            query.setMaxResults(1);
            List<Customer> customers = query.getResultList();*/
            Query nativeQuery = em.createNativeQuery("Select * from customer", Customer.class);
            List<Customer> customers = nativeQuery.getResultList();
            System.out.println("All Customers : ");
            //System.out.println("Customer with Ali as first name : ");
            customers.forEach((cc) -> {
                System.out.println(cc.getId() +" - "+cc.getFirstName()+" - "+ cc.getEmail());
                
                //System.out.println("Le customer n° "+c.getId()+" a été supprimé avec succés");
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
