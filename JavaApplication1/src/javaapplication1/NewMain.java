/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author YASSALIE
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Etudiant e1= new Etudiant(1,"sbai","yasser","fes","01/01/1985");
        Etudiant e2= new Etudiant(2,"alaoui","mohamed","rabat","01/01/1965");
        System.out.println(e1.getAge());
        System.out.print(e2.isSameAge(e1));
        e1.setDateNaiss("01/01/1965");
        System.out.print(e2.isSameAge(e1));
    }
    
}
