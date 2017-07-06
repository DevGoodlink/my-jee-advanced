/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observabledesignpattern;

import javax.swing.JOptionPane;

/**
 *
 * @author YASSALIE
 */
public class ObservateurConcret implements Observateur{
    protected SujetConcret sujetConcret;
    public ObservateurConcret(SujetConcret sc) {
        this.sujetConcret = sc;
        sc.ajouterObservateur(this);
    }

    @Override
    public void actualiser() {
        JOptionPane.showMessageDialog(null, "Je suis l'un des observateur \n l'etat de l'objet concret a été modifié");
    }
    
}
