/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import model.Product;

/**
 *
 * @author YASSALIE
 */
@ManagedBean
@ApplicationScoped
public class CatalogueManager implements Serializable {
    private ArrayList<Product> lstPdt;
    private String id, name;
    private double price;
    public CatalogueManager() {
        this.lstPdt = new ArrayList<>();
    }
    @PostConstruct
    public void initMethod(){
        Product p = new Product();
        Product p0 = new Product();
        Product p1 = new Product();
        Product p2 = new Product();
        p.setProductId("p11");p.setProductName("Souris optique");p.setProductPrice(50.99);
        p0.setProductId("p12");p0.setProductName("Clavier sans fil");p0.setProductPrice(69.99);
        p1.setProductId("p13");p1.setProductName("Webcam");p1.setProductPrice(9.99);
        p2.setProductId("p12");p2.setProductName("Disque dur");p2.setProductPrice(200);
        this.lstPdt.add(p);lstPdt.add(p0);lstPdt.add(p1);lstPdt.add(p2);
        System.out.println("Chargement de la liste");
    }
    public ArrayList<Product> getLstPdt() {
        return lstPdt;
    }

    public void setLstPdt(ArrayList<Product> lstPdt) {
        this.lstPdt = lstPdt;
    }
    public int getListSize(){
        return this.lstPdt.size();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    public String createProduct(){
        Product p = new Product();
        p.setProductId(this.getId());
        p.setProductName(this.getName());
        p.setProductPrice(this.getPrice());
        this.lstPdt.add(p);
        return "tocatalogue";
    }
}
