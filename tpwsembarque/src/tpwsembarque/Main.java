/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpwsembarque;

import javax.xml.ws.Endpoint;
import ws.PhoneValidator;

/**
 *
 * @author YASSALIE
 */
public class Main {

    
    public static void main(String[] args) {
        
        String wsaddress = "http://localhost:8766/tpwsembarque/PhoneValidator";
        Endpoint.publish(wsaddress, new PhoneValidator());
        System.out.println("Web service lunched successfully");
        System.out.println(wsaddress + "?WSDL");
        while(Thread.currentThread().isAlive()) {
            try{
                Thread.sleep(1000);
            }catch(InterruptedException ex){
                
            }
        }


    }
    
}
