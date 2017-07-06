/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpatternfactory;

public class AnimalFactory {

	public Animal CreateAnimal(String type) {
		if ("Chat".equals(type)) {
			return new Cat();
		} else if ("Chien".equals(type)){
			return new Dog();
		}else if ("Cheval".equals(type)){
			return new Horse();
		}
                
                else
                { return null;}
	}

}