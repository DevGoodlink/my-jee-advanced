/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpattern.memento.exemple1;

import java.util.ArrayList;

/**
 *
 * @author Imad
 */
public class CareTaker {
    private ArrayList savedCheckpoints = new ArrayList();
 
    public void addMemento(Object m) { savedCheckpoints.add(m); }
    public Object getMemento(int index) { return savedCheckpoints.get(index); }
}
