/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author YASSALIE
 */
public class Mail {
    public static final int DEMANDE_ENREGISTREE = 1;
    public static final int NOUVELLE_DEMANDE =2;
    public static final int ATTESTATION_VERIFIE=4;
    public static final int ATTESTATION_VALIDEE=5;
    public static void prepareMail(String to,int type,String fileName){
        switch(type){
            case 1:
                send(null,"Demande d'attestation de stage enregistrée","Votre demande a été prise en considération",to);
            break;
            case 2:
                send(fileName,"Votre demande d'attestation de stage","Veuillez trouver en pièce jointe votre attestation de stage. \n Coordialement",to);
            break;
            case 3:
                
            break;
        }
    }
    private static void send(String fileName,String subject,String corps,String to){
        try{
            System.out.println("Ok Début envoi mail");
            //https://www.google.com/settings/security/lesssecureapps
            // Données de configuration pour ouvrir une session
            Properties prop = new Properties();
            prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            prop.put("mail.transport.protocol", "smtp");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true");
            Session session = Session.getInstance(prop, null);
            
            //péparer un objet Message
            Message courier = new MimeMessage(session);
            courier.setFrom(new InternetAddress("jmssenderchu@gmail.com "));
            courier.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            courier.setSubject(subject);//sujet
            
            Multipart multipart = new MimeMultipart();
            BodyPart corpsCourier = new MimeBodyPart();
            BodyPart textMessage = new MimeBodyPart();
            ///-----------------
            System.out.println("uploading picture");
            String filename = fileName;
            DataSource source = new FileDataSource("files/"+filename);
            corpsCourier.setDataHandler(new DataHandler(source));
            corpsCourier.setFileName(filename);
            textMessage.setText("M. \n"+corps);
            multipart.addBodyPart(corpsCourier);
            multipart.addBodyPart(textMessage);
            System.out.println("end of process picture");
//-----------------
            
            courier.setContent(multipart);
            Transport tp = session.getTransport("smtp");
            tp.connect("smtp.gmail.com","jmssenderchu","upf2017*");
            System.out.println("passs");
            tp.sendMessage(courier, courier.getAllRecipients());
            System.out.println("Ok apres SendMesage");
            tp.close();
            System.out.println("Message envoyé avec succés");
        }catch(Exception ex){
            System.out.println(""+ex.getMessage());
        }
    }
}
