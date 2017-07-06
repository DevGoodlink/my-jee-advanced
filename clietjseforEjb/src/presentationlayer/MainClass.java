/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentationlayer;

import businesslogicallayer.CalculatorEJBRemote;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Scanner;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author YASSALIE
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez entrer la valeur de a");
        int a = sc.nextInt();
        System.out.println("Veuillez entrer la valeur de b");
        int b = sc.nextInt();

        try {
            System.out.println("Ouverture de liaison avec le serveur");
            Properties pro = new Properties();
            pro.load(new FileInputStream("jndi.properties"));
            Context ctx = new InitialContext(pro);
            CalculatorEJBRemote calcultorEjb = (CalculatorEJBRemote) ctx.lookup("java:global/ejbtp1calculator/CalculatorEJB!businesslogicallayer.CalculatorEJBRemote");
            System.out.println("Serveur trouvé, réception de la réponse");

            System.out.println("Résultat de la multiplication de " + a + " et " + b + " est égale à " + calcultorEjb.mul(a, b));
        } catch (Exception e) {

        }
    }

}
