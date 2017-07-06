package weblayer;

import businesslogiclayer.BookEJB;
import businesslogiclayer.PanierEJB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import persistence.Book;

@ManagedBean
@SessionScoped
public class BookController implements Serializable {
    @EJB
    private BookEJB bookEJB;
    @EJB
    private PanierEJB pe;
   
    private Book book=new Book();
    private List<Book> bookList = new ArrayList<>();
    private List<Long> selectedBookIds = new ArrayList<>();
    private Map<Long,Boolean> selectedIds = new HashMap<>();
    private List<Book> selectedBooks  = new ArrayList<>();
    
    public BookController() {
        
    }

    public List<Long> getSelectedBookIds() {
        return selectedBookIds;
    }

    public void setSelectedBookIds(List<Long> selectedBookIds) {
        this.selectedBookIds = selectedBookIds;
    }

    public Map<Long, Boolean> getSelectedIds() {
        return selectedIds;
    }

    public void setSelectedIds(Map<Long, Boolean> selectedIds) {
        this.selectedIds = selectedIds;
    }

    public List<Book> getSelectedBooks() {
        return selectedBooks;
    }

    public void setSelectedBooks(List<Book> selectedBooks) {
        this.selectedBooks = selectedBooks;
    }
    
    public String doCreateBook(){
        this.book=bookEJB.(book);
        this.bookList=bookEJB.findBooks();
        return "listBooks.xhtml";
    }
    public Book getBook(){
        return this.book;
    }
    public void setBook(Book book){
        this.book=book;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public BookEJB getBookEJB() {
        return bookEJB;
    }

    public void setBookEJB(BookEJB bookEJB) {
        this.bookEJB = bookEJB;
    }

    public PanierEJB getPe() {
        return pe;
    }

    public String addBooks(){
        for (Entry<Long,Boolean> entry : selectedIds.entrySet()) {
            Long cle = entry.getKey();
            Boolean valeur = entry.getValue();
            if(valeur){
                pe.addBooks(getBookById(cle));
            }
        }
        return "panier";
    }
    public Book getBookById(Long id){
        
        for (Book b : bookList) {
            if(b.getId().equals(id)){
                return b;
            }
        }
        return null;
    }
    
    
    
}
