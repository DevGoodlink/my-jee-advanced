/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.DemandeFacade;
import entity.Demande;
import entity.User;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author YASSALIE
 */
@Named(value = "controller")
@SessionScoped
public class Controller implements Serializable {

    @EJB
    private DemandeFacade demandeFacade;
    private User user=new User();
    private Demande demande=new Demande();
    public Controller() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }
    public String doCreate(){
        if(demande.getEmail().isEmpty())
            
    }
    
}
