package designpattern.memento.exemple2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Imad
 */
// Memento Design Pattern Tutorial

import java.util.ArrayList;

class Caretaker {
   
	// Where all mementos are saved
	
	ArrayList<Memento> savedArticles = new ArrayList<Memento>();

	// Adds memento to the ArrayList
	
	public void addMemento(Memento m) { savedArticles.add(m); }
   
	// Gets the memento requested from the ArrayList
	
	public Memento getMemento(int index) { return savedArticles.get(index); }
} 
