package beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.Statusattestation;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import sessionbean.StatusattestationFacade;

/**
 *
 * @author YASSALIE
 */
@Named(value = "statusAttesMB")
@SessionScoped
public class StatusAttesMB implements Serializable {
    private int id;
    private String libelle;
    
    @EJB
    private StatusattestationFacade statusattestationFacade;
    
    Statusattestation st = new Statusattestation();

    /**
     * Creates a new instance of StatusAttesMB
     */
    public StatusAttesMB() {
    }
    public List<Statusattestation> findAll(){
        return this.statusattestationFacade.findAll();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public String createStatus(){
       
        this.statusattestationFacade.create(this.st);
        return "success";
    }

    public Statusattestation getSt() {
        return st;
    }

    public void setSt(Statusattestation st) {
        this.st = st;
    }
    
}
