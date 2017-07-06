/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prj_except2;

public class Except1 {
    public static void main(String args[]){
        try {
            Point a = new Point(1,4);
            a.affiche();
            a=new Point(-3,5);
            System.out.println("Dans try");
            a.affiche();
            
        }catch(ExceptionConstruction e){
            System.out.println("Erreur construction");
            System.out.println(e.getMessage());
        }
    }
    
}
