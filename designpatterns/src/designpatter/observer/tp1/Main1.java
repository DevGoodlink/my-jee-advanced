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
public class Main1 {
    public static void main(String[] args){
        SujetConcret sc = new SujetConcret();
        ObserverConcret obc = new ObserverConcret(sc);
        
        sc.setEtat(5);
    }
}
