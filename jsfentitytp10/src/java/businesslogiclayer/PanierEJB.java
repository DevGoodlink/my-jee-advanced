/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogiclayer;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import persistencelayer.Book;

/**
 *
 * @author YASSALIE
 */
@Stateful
public class PanierEJB {

  private List<Book> books;

    public PanierEJB() {
        books = new ArrayList<>();
    }
    @PostConstruct
    public void init(){
        System.out.println("Exécution automatique d la méthode init");
    }
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
    public void addBooks(Book b){
        books.add(b);
        System.out.println("Books ajouté  = "+b.getIsbn());
        System.out.println("Books size = "+books.size());
    }
  
}
