/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observabledesignpattern;

/**
 *
 * @author YASSALIE
 */
public class SujetConcret extends Sujet {
    double etat;

    public double getEtat() {
        return etat;
    }

    public void setEtat(double etat) {
        this.etat = etat;
        this.notifierObservateurs();
    }

    
}
