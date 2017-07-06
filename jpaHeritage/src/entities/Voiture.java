/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "ID")
public class Voiture extends Vehicule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name="nbrPortes")
    private int nbrePorte;
    @Column(name="Gamme")
    private String gamme;

    public int getNbrePorte() {
        return nbrePorte;
    }

    public void setNbrePorte(int nbrePorte) {
        this.nbrePorte = nbrePorte;
    }

    public String getGamme() {
        return gamme;
    }

    public void setGamme(String gamme) {
        this.gamme = gamme;
    }
    
}
