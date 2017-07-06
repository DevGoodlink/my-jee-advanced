/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogiclayer;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import persistencelayer.Book;

/**
 *
 * @author YASSALIE
 */
@Stateless
public class BookEJB {
    
    
    public Book createBook(Book book){
        
        em.persist(book);
        return book;
    }
    public List<Book> findBooks(){
        TypedQuery<Book> query = em.createNamedQuery("findAllBooks", Book.class);
        return query.getResultList();
    }

    public void persist(Object object) {
        em.persist(object);
    }
}
