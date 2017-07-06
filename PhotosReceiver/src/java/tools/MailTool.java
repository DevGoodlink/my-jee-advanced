/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.Properties;
import java.util.ResourceBundle;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author YASSALIE
 */
public class MailTool {

	public static void sendMail(String fileName){
        try{
            System.out.println("Ok Début");
            //https://www.google.com/settings/security/lesssecureapps
            // Données de configuration pour ouvrir une session
            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.transport.protocol", "smtp");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true");//securité
            Session session = Session.getInstance(prop, null);
            
            //péparer un objet Message
            Message courier = new MimeMessage(session);
            courier.setFrom(new InternetAddress("goodlink.fes@gmail.com"));
            courier.setRecipient(Message.RecipientType.TO, new InternetAddress("sbaiyasser32@gmail.com"));
            courier.setSubject("Facture");
            
            Multipart multipart = new MimeMultipart();
            BodyPart corpsCourier = new MimeBodyPart();
            corpsCourier.setText("Montant de la facture = 300DH");
            ///-----------------
            String filename = fileName;
            DataSource source = new FileDataSource("photos/"+filename);
            corpsCourier.setDataHandler(new DataHandler(source));
            corpsCourier.setFileName(filename);
            multipart.addBodyPart(corpsCourier);
//-----------------
            
            courier.setContent(multipart);
            Transport tp = session.getTransport("smtp");
            tp.connect("smtp.gmail.com","goodlink.fes","nathalie40");
            System.out.println("passs");
            tp.sendMessage(courier, courier.getAllRecipients());
            System.out.println("Ok apres SendMesage");
            tp.close();
            System.out.println("fin");
        }catch(Exception ex){
            //Logger.getLogger(SendMailTimerEJB.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
}
