/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmstp2receiver;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Jmstp2receiver implements MessageListener {

    public Jmstp2receiver() throws NamingException, JMSException {
        Context ctx = new InitialContext();
        ConnectionFactory fabrique = (ConnectionFactory)ctx.lookup("jms/Tp2ConnectionFactory");
        Destination destination = (Destination)ctx.lookup("jms/Tp2Destination");
        Connection connexion = fabrique.createConnection();
        
        Session session = connexion.createSession(false,Session.AUTO_ACKNOWLEDGE);
        MessageConsumer reception =session.createConsumer(destination);
        reception.setMessageListener(this);
        connexion.start();
        connexion.stop();
    }

    @Override
    public void onMessage(Message arg) {
        try {
            TextMessage message = (TextMessage) arg;
            System.out.println(message.getText());
        } catch (UnsupportedOperationException e) {
            System.out.println("Ereur : "+e.getMessage());
        } catch (JMSException ex) {
            Logger.getLogger(Jmstp2receiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) throws NamingException, JMSException {
        new Jmstp2receiver();
    }
    
    
}
