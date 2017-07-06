/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpatter.observer.tp1;

import javax.swing.JOptionPane;

/**
 *
 * @author Imad
 */
public class ObserverConcret implements Observateur{

    protected SujetConcret sujetConcret;
    
    public ObserverConcret(SujetConcret s){
        this.sujetConcret = s;
        sujetConcret.enregisterObserver(this);
    }
   
    public void actualiser() {
       JOptionPane.showMessageDialog(null,"Je suis l'un des observers" + "notification mem montrant que l'objet sujet concret s'est changé");
    }
    
}
