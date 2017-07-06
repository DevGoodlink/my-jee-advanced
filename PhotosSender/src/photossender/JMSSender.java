/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photossender;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.jms.ConnectionFactory;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JMSSender {
    public static String send(String nomPhoto) throws NamingException, JMSException, FileNotFoundException, IOException{
        Context ctx = new InitialContext();
        ConnectionFactory fabrique = (ConnectionFactory)ctx.lookup("jms/Tp2ConnectionFactory");
        Destination destination = (Destination)ctx.lookup("jms/Tp2Destination");
        try (Connection connexion = fabrique.createConnection()) {
            System.out.println("Début de la connexion.");
            Session session = connexion.createSession(false,Session.AUTO_ACKNOWLEDGE);
            MessageProducer envoi =session.createProducer(destination);
            File f = new File("photos/"+nomPhoto);
            System.out.println("Fichier créé.");
            FileInputStream fis = new FileInputStream(f);
            
            System.out.println("image créée.");
            byte[] data = new byte[(int)f.length()];
            System.out.println("tableau de byte créé.");
            fis.read(data);
            System.out.println("FileInputStream chargé.");
            
            StreamMessage message = session.createStreamMessage();
            message.setStringProperty("nomPhoto", nomPhoto);
            //message.writeString(nomPhoto);
            message.setIntProperty("taille",data.length );
            //message.writeInt(data.length);
            message.writeBytes(data);
            envoi.send(message);
            System.out.println("Packet envoyé");
           //TextMessage message = session.createTextMessage();
            //message.setText(msg);
            //envoi.send(message);
        }
        return "Ok  :  message envoyé";
    }
}
