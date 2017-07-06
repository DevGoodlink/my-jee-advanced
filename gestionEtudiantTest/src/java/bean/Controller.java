/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.Filiere;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.*;
import model.FiliereFacade;
//import model.EtudiantFacade;

/**
 *
 * @author YASSALIE
 */
@ManagedBean
@SessionScoped
public class Controller implements Serializable {

    @EJB
    private FiliereFacade filiereFacade;
    
    private Long idStdt;
    private String nom,prenom;
    private Date ddn;
    private ArrayList<Filiere> lstF;
    private Filiere filiere;
    {
        lstF=(ArrayList)filiereFacade.findAll();
    }
    public Controller() {
        
    }

    public Long getIdStdt() {
        return idStdt;
    }

    public void setIdStdt(Long idStdt) {
        this.idStdt = idStdt;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDdn() {
        return ddn;
    }

    public void setDdn(Date ddn) {
        this.ddn = ddn;
    }

    public ArrayList<Filiere> getLstF() {
        return lstF;
    }

    public void setLstF(ArrayList<Filiere> lstF) {
        this.lstF = lstF;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }
    
    
}
