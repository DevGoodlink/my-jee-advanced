/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import model.Product;

/**
 *
 * @author YASSALIE
 */

public class ShoppingCartItem implements Serializable {

    private int id,qte;
    private Product product;
    private ArrayList<Product> lstPdt;
    public ShoppingCartItem() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
}
