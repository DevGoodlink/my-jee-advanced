/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entitie.*;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author YASSALIE
 */
@Named(value = "ajoutAttestation")
@SessionScoped
public class ajoutAttestation implements Serializable {
    private Date date_at;
    private User stagiaire,Signateur,Livreur;
    private Stage stage;
    private String filename;
    private Statusattestation statt;
    
    private Attestation attestation;

    public Attestation getAttestation() {
        return attestation;
    }

    public void setAttestation(Attestation attestation) {
        this.attestation = attestation;
    }
    public ArrayList<User> allStagiaire(){
        
        
    }
    public ajoutAttestation() {
    }

    public Date getDate_at() {
        return date_at;
    }

    public void setDate_at(Date date_at) {
        this.date_at = date_at;
    }

    public User getStagiaire() {
        return stagiaire;
    }

    public void setStagiaire(User stagiaire) {
        this.stagiaire = stagiaire;
    }

    public User getSignateur() {
        return Signateur;
    }

    public void setSignateur(User Signateur) {
        this.Signateur = Signateur;
    }

    public User getLivreur() {
        return Livreur;
    }

    public void setLivreur(User Livreur) {
        this.Livreur = Livreur;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Statusattestation getStatt() {
        return statt;
    }

    public void setStatt(Statusattestation statt) {
        this.statt = statt;
    }
    public String createAttestation(){
        
        return "tohome";
    }
}
