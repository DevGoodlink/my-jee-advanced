/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prj_except;

import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author YASSALIE
 */
public class DivZeroAvecGestionExcep {
    
    public static void main(String[] args) {
    System.out.println("Entrer un entier");
        Scanner sc= new Scanner(System.in);
        
        int a = sc.nextInt();
        try{
            int b=200/a;
            System.out.println("Résultat de la division de 200 / "+ a+" est égale à : "+b);
        }catch(ArithmeticException e){
             System.out.println("Erreur "+e.getMessage());
             JOptionPane.showMessageDialog(null,"Division par zéro");
        }
        
        
       
    }
    
}
