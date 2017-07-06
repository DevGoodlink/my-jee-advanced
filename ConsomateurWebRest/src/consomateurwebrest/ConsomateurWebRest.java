/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consomateurwebrest;

import rest.NewJerseyClient;

/**
 *
 * @author ahmed2
 */
public class ConsomateurWebRest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NewJerseyClient clientRest= new NewJerseyClient();
        System.out.println(clientRest.getHtml());
    }
    
}
