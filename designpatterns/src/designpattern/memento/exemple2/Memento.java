/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpattern.memento.exemple2;

/**
 *
 * @author Imad
 */
// Memento Design Pattern
// Used stores an objects state at a point in time
// so it can be returned to that state later. It
// simply allows you to undo/redo changes on an Object

public class Memento {
	
	// The article stored in memento Object
	
	private String article;

	// Enregistre un nouveau article dans l'objet Memento
	
	public Memento(String articleSave) { article = articleSave; }
	
	// Retourne la valeur stocker dans article
	
	public String getSavedArticle() { return article; }
	
}
