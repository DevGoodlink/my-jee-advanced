/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prj_except4;


public class Except4 {
  public static void main (String args[]){
      try{
          Point a =new Point(1,4);
          a.affiche();
          a.deplacer(-3, 5);
          a=new Point(-3,5);
          a.affiche();
      }catch(ExceptionPoint e){
          System.out.println(e.getTypeException());
          System.out.println(e.getMessage());
          System.out.println("information concernant l'exception la valeur du champ info = "+e.info);
      }
  }  
}
