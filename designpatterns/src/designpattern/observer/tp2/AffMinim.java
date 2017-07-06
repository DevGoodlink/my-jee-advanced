/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpattern.observer.tp2;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Imad
 */
public class AffMinim implements Observer{

    @Override
    public void update(Observable o, Object arg) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        HeureCourante h= (HeureCourante) o;
        System.out.println("Il est "+h.getHeure()+":"+h.getMinute()+":"+h.getSeconde());
    }
    
}
