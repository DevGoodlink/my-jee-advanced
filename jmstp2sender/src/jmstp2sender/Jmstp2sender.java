/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmstp2sender;
import javax.jms.ConnectionFactory;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class Jmstp2sender {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NamingException, JMSException {
        Context ctx = new InitialContext();
        ConnectionFactory fabrique = (ConnectionFactory)ctx.lookup("jms/Tp2ConnectionFactory");
        Destination destination = (Destination)ctx.lookup("jms/Tp2Destination");
        Connection connexion = fabrique.createConnection();
        
        Session session = connexion.createSession(false,Session.AUTO_ACKNOWLEDGE);
        MessageProducer envoi =session.createProducer(destination);
        TextMessage message = session.createTextMessage();
        message.setText("Ma première application JMS");
        envoi.send(message);
        connexion.close();
        System.out.println("Ok  :  message envoyé");
    }
    
}
