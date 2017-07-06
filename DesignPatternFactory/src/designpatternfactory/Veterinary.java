/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpatternfactory;

public class Veterinary {
    private AnimalFactory Fabrique;

    public Veterinary(AnimalFactory Fabrique) {
        this.Fabrique = Fabrique;
    }
    
    public Animal ListenSound(String type)
    {
        Animal A = Fabrique.CreateAnimal(type);
        A.makeSound();
        return A;
    }
     
     
}
