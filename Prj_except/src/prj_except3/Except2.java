/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prj_except3;

public class Except2 {
    public static void main(String args[]){
        try{
            Point a=new Point(1,4);
            a.affiche();
            a.deplacer(-3, 5);
            a=new Point (-3,5);
            a.affiche();
            
        }catch(ExceptionDeplacement e){
            System.out.println("Erreur de déplacement");
        }catch(ExceptionConstruction e){
            System.out.println("Erreur de construction");
        }
        /*on peut gérer les deux exceptions 
        à l'aide de la classe Exception dans un seul bloc catch(Exception e){traitement}
        */
        
    }
}
