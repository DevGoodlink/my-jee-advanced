/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prj_except;

import java.util.Scanner;



/**
 *
 * @author YASSALIE
 */
public class Prj_except {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Entrer un entier");
        Scanner sc= new Scanner(System.in);
        int a = sc.nextInt();
        int b=200/a;
        
        System.out.println("Résultat de la division de 200 / "+ a+" est égale à : "+b);
        
    }
    
}
