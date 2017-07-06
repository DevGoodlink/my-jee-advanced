/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import javax.persistence.Column;

/**
 *
 * @author YASSALIE
 */
public abstract class Vehicule {
    @Column(name="matricule")
    private String matricule;
    @Column(name="marque")
    private String marque;

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

   

   

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }
    
}
