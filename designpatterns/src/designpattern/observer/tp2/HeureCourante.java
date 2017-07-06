/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpattern.observer.tp2;

import java.util.Calendar;
import java.util.Observable;

/**
 *
 * @author Imad
 */
public class HeureCourante extends Observable implements Runnable{
    int heure,minute,seconde;
    Calendar cal;

    public HeureCourante() {
        //this.run();
    }
    
    public int getHeure() {
        return heure;
    }

    public void setHeure(int heure) {
        this.heure = heure;
        this.setChanged();
        this.notifyObservers();
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
        this.setChanged();
        this.notifyObservers();
    }

    public int getSeconde() {
        return seconde;
    }

    public void setSeconde(int seconde) {
        this.seconde = seconde;
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public void run() {
        while (true) {  
                    try {
                        cal = Calendar.getInstance();
                        this.setHeure(cal.get(Calendar.HOUR));
                        this.setMinute(cal.get(Calendar.MINUTE));
                        this.setSeconde(cal.get(Calendar.SECOND));
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
        }
    }
    
    
}
