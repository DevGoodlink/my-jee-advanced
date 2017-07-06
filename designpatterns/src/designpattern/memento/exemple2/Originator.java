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

public class Originator{
	
	private String article;

	// Sets la valeur pour l'article
	
	public void set(String newArticle) { 
		System.out.println("Depuis Originator: La version courante de l'article\n"+newArticle+ "\n");
	    this.article = newArticle; 
	}

	// Creation d'un nouveau Memento avec un article
	
	public Memento storeInMemento() { 
	    System.out.println("Depuis Originator: Saving to Memento");
	    return new Memento(article); 
	}
	   
	 // Retourne l'article recement enregistrer sur Memnto
	
	public String restoreFromMemento(Memento memento) {
		   
		article = memento.getSavedArticle(); 
	       
		System.out.println("Depuis Originator: Previous Article Saved in Memento\n"+article + "\n");
		
		return article;
	   
	}
	
}
