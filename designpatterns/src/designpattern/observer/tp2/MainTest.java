/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpattern.observer.tp2;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Imad
 */
public class MainTest {
    public static void main(String[] args) {
        
        HeureCourante h = new HeureCourante();
        AffComplet ac = new AffComplet();
        AffMinim am = new AffMinim();
        h.addObserver(ac);
        h.addObserver(am);
        h.run();
    }
   
}
