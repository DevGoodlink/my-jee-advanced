/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weblayer;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import persistence.Book;
/**
 *
 * @author YASSALIE
 */
@Named(value = "panierBeanController")
@SessionScoped
public class PanierBeanController implements Serializable {
    @ManagedProperty(value="#{bookController}")
    private BookController bc;
    private List<Book> books;
    
    public PanierBeanController() {
        
    }
    
    @PostConstruct
    public void init(){
        books = bc.getPe().getBooks();
        System.out.println("Init : "+books.size());
    }
    
}
