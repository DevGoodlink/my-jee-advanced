/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpatter.observer.tp1;

/**
 *
 * @author Imad
 */
public class SujetConcret extends Sujet {
     
    double etat;
    
    public double getEtat(){
        return etat;
    }
    
    public void setEtat(double e){
        this.etat = e;
        this.notifierObserver();
    }
}
