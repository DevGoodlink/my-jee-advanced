/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpatter.observer.tp1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Imad
 */
public abstract class Sujet {
    
    List<Observateur> observers = new ArrayList<>();
    
    public void enregisterObserver(Observateur o){
        observers.add(o);
    }
    public void removeObserver(Observateur o){
        observers.remove(o);
    }
    
    public void notifierObserver(){
        for(Observateur o : observers){
            o.actualiser();
        }
    }
    
}
