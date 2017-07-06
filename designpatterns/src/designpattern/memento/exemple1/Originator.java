/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpattern.memento.exemple1;

/**
 *
 * @author Imad
 */
public class Originator {
     private String checkpoint;
 
    public void set(String checkpoint) { 
        System.out.println("Originator: etat affecte a: "+checkpoint);
        this.checkpoint = checkpoint; 
    }
 
    public Object saveToMemento() { 
        System.out.println("Originator: sauvegarde dans le memento.");
        return new Memento(checkpoint); 
    }
    public void restoreFromMemento(Object m) {
        if (m instanceof Memento) {
            Memento memento = (Memento)m; 
            checkpoint = memento.getSavedState(); 
            System.out.println("Originator: Etat après restauration: "+checkpoint);
        }
    }
}
