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
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.Context;

/**
 *
 * @author Imad
 */
@Named(value = "controller")
@SessionScoped
public class Controller implements Serializable {

    @EJB
    private DemandeFacade demandeFacade;
    
    private User user;
    private Demande demande;
    private List<Demande> lstDemandeSecretaires;
    private String erreur;
    
    private Demande demandeToVerify;
    public Controller() {
        
    }
    @PostConstruct
    public void init(){
        user=new User();
        demande=new Demande();
        lstDemandeSecretaires = demandeFacade.listDemandes();
    }

    public Demande getDemandeToVerify() {
        return demandeToVerify;
    }

    public void setDemandeToVerify(Demande demandeToVerify) {
        this.demandeToVerify = demandeToVerify;
    }
    
    public List<Demande> getLstDemandeSecretaires() {
        return lstDemandeSecretaires;
    }

    public void setLstDemandeSecretaires(List<Demande> lstDemandeSecretaires) {
        this.lstDemandeSecretaires = lstDemandeSecretaires;
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

    public String getErreur() {
        return erreur;
    }

    public void setErreur(String erreur) {
        this.erreur = erreur;
    }

    public String doCreate(){
        if(!demande.getNom().isEmpty() && !demande.getPrenom().isEmpty() && !demande.getEmail().isEmpty() && !demande.getSujetStage().isEmpty() && !demande.getEtablissement().isEmpty()){
            demande.setDateDemande(new Date());
            demandeFacade.create(demande);
            this.init();
            return "tosuccess";
        }
        else{
              erreur = "Inserer le nom";
              return erreur;
        }
    }
    public String doVerify(){
        if(demandeToVerify!=null){
            demandeToVerify.setEtatDemande(Demande.VERIFIEE);
            demandeFacade.edit(demandeToVerify);
            this.init();
            return "tolistdemande";
        }
        return null;
    }
    public String doUnVerify(){
        if(demandeToVerify!=null){
            demandeToVerify.setEtatDemande(Demande.NON_VRIFIEE);
            demandeFacade.edit(demandeToVerify);
            this.init();
            return "tolistdemande";
        }
        return null;
    }
    public String comeback(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "toindex";
    }
}
