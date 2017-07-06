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
public class MainTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SujetConcret sujetConcret = new SujetConcret();
        
        ObservateurConcret observateurConcret = new ObservateurConcret(sujetConcret);
        
        sujetConcret.setEtat(5);
    }
    
}
