/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpconsomateurserviceweb;

import servicecalculatrice.Calculatrice;
import servicecalculatrice.Calculatrice_Service;

/**
 *
 * @author YASSALIE
 */
public class TpConsomateurServiceWeb {

    public static void main(String[] args) {
        Calculatrice_Service  service = new Calculatrice_Service();
        Calculatrice port = service.getCalculatricePort();
        
        System.out.println("Message de bienvenue "+port.hello("Yasser"));
        System.out.println("Addition de 2 et 3 "+port.additionner(2, 3));
        System.out.println("Soustraction de 2 et 3 "+port.soustraire(2, 3));
        System.out.println("Multiplication de 2 et 3 "+port.multiplier(2, 3));
    }
    
}
