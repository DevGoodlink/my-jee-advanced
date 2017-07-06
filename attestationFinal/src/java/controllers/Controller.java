/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.DemandeFacade;
import beans.UserFacade;
import entity.Demande;
import entity.User;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author Imad
 */
@Named(value = "controller")
@SessionScoped
public class Controller implements Serializable {

    @EJB
    private UserFacade userFacade;
    private List<User> userList;
    
    @EJB
    private DemandeFacade demandeFacade;
    
    private Demande demandeToVerify;
    private User user;
    private Demande demande;
     
    private String login,password;
    
    private List<Demande> listDemande;
    private List<Demande> listDemandeVerifier ;
    
//    private String nom,prenom,description,etablissement,sujetStage,email;
//    private Date dateDebut,dateFin;
    
    private String erreur;
    
    /**
     * Creates a new instance of Controller
     */
    public Controller() {
               
    }
    @PostConstruct
    public void init(){
        
        user=new User();
        demande=new Demande();
        listDemande = demandeFacade.findOld();
        listDemandeVerifier = demandeFacade.findVerified();
        
    }

    public Demande getDemandeToVerify() {
        return demandeToVerify;
    }

    public void setDemandeToVerify(Demande demandeToVerify) {
        this.demandeToVerify = demandeToVerify;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

     public String authentificate() throws Exception{
        List<User> lstUser = getItems() ;
        for(User u : lstUser){
            if(u.getLogin().equalsIgnoreCase(login) && u.getPassword().equals(password) && u.getGrade()==1)
                return "tolistdemande";
            else if(u.getLogin().equalsIgnoreCase(login) && u.getPassword().equals(password) && u.getGrade()==2)
                return "toadmin";
        }
       // erreur="Login ou mot de passe incorrect";
       return null;
    }

    public List<User> getUserList() {
        return userList;
    }

    public List<Demande> getListDemandeVerifier() {
        return listDemandeVerifier;
    }

    public void setListDemandeVerifier(List<Demande> listDemandeVerifier) {
        this.listDemandeVerifier = listDemandeVerifier;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
     
     public List<User> getItems() {
        if (userList == null) {
            userList = userFacade.findAll();
        }
        return userList;
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


    public List<Demande> getListDemande() {
        return listDemande;
    }

    public void setListDemande(List<Demande> listDemande) {
        this.listDemande = listDemande;
    }
    
    public String doCreate(){
        if(demande.getNom().isEmpty() && demande.getPrenom().isEmpty() && demande.getEmail().isEmpty() && demande.getDescription().isEmpty() && demande.getEtablissement().isEmpty()){
                this.erreur = "Veuillier remplir les champs";
        }else{
            demande.setDateDemande(new Date());
            this.demandeFacade.create(demande);
            
            return "tosuccess";
        }
      return null;
    }

    public String comeback(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "toindex";
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
}
