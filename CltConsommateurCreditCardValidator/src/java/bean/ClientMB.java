/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.xml.ws.WebServiceRef;


/**
 *
 * @author YASSALIE
 */
@Named(value = "clientMB")
@SessionScoped
public class ClientMB implements Serializable {
   
    
    
    private CreditCard cc = new CreditCard();
    
    public ClientMB() {
    }

    public CreditCard getCc() {
        return cc;
    }

    public void setCc(CreditCard cc) {
        this.cc = cc;
    }

    private void create() {
        
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        ws.CreditCardWS port = service.getCreditCardWSPort();
        port.create(cc);
        
    }
    
   
}
