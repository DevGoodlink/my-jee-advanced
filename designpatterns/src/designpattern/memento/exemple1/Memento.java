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
public class Memento {
    
     private String checkPoint;
 
        public Memento(String stateToSave) { checkPoint = stateToSave; }
        public String getSavedState() { return checkPoint; }
        
}
