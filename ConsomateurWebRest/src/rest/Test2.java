/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ahmed2
 */
public class Test2 {
    
    public static void main(String args[]){
       String BASE_URI = "http://localhost:8080/wsRest/webresources";

       Client client=ClientBuilder.newClient();
        WebTarget resource=client.target(BASE_URI).path("helloworld");
       
        System.out.println(resource.request(MediaType.TEXT_HTML).get(String.class));
        
        
    }
    
}
