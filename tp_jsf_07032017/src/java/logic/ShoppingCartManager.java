package logic;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.ShoppingCartItem;
import model.Product;

/**
 *
 * @author YASSALIE
 */
@ManagedBean
@SessionScoped
public class ShoppingCartManager implements Serializable {
   private ArrayList<ShoppingCartItem> lstProOnCart;
   private Product prodToAdd;
   private ShoppingCartItem prodToRemove;
    public ShoppingCartManager() {
        lstProOnCart = new ArrayList<>();
    }
    @PostConstruct
    public void initCart(){
        
    }

    public ShoppingCartItem getProdToRemove() {
        return prodToRemove;
    }

    public void setProdToRemove(ShoppingCartItem prodToRemove) {
        this.prodToRemove = prodToRemove;
    }
    
    public Product getProdToAdd() {
        return prodToAdd;
    }

    public void setProdToAdd(Product prodToAdd) {
        this.prodToAdd = prodToAdd;
    }

    public ArrayList<ShoppingCartItem> getLstProOnCart() {
        return lstProOnCart;
    }

    public void setLstProOnCart(ArrayList<ShoppingCartItem> lstProOnCart) {
        this.lstProOnCart = lstProOnCart;
    }
    
    public String addToCart(){
        ShoppingCartItem s = new ShoppingCartItem();
        s.setProduct(prodToAdd);
        s.setId(1);
        s.setQte(1);
        lstProOnCart.add(s);
        return "toshoppingcart";
    }
    public String removeFromCart(){
        
        lstProOnCart.remove(prodToRemove);
        return "toshoppingcart";
    }
}
