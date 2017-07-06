/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import java.util.Properties;
import java.util.ResourceBundle;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author YASSALIE
 */
public class MailTool {

	/**
	 * Ressource contenant les éléments statiques liés à la création et l'envoi d'un email.
	 */
	//private static ResourceBundle smtpBundle = ResourceBundle.getBundle("smtp");

	/**
	 * Envoi d'un email utilisant une socket SSL (SSLSocketFactory).
	 * @param to celui ou ceux qui doivent recevoir l'email (séparation des adresses par des virgules)
	 * @param subject sujet de l'email
	 * @param content contenu de l'email
	 * @throws AddressException les adresses de destinations sont incorrectes
	 * @throws MessagingException une erreur est survenue à l'envoi de l'email
	 */
	public static void sendEmailSSL(String to, String subject, String content) throws AddressException, MessagingException {
		// smtp properties
		Properties props = new Properties();
		/*props.put("mail.smtp.host", smtpBundle.getString("mail.smtp.host"));
		props.put("mail.smtp.socketFactory.port", smtpBundle.getString("mail.smtp.socketFactory.port"));
		props.put("mail.smtp.socketFactory.class", smtpBundle.getString("mail.smtp.socketFactory.class"));
		props.put("mail.smtp.auth", smtpBundle.getString("mail.smtp.auth"));
		props.put("mail.smtp.port", smtpBundle.getString("mail.smtp.port"));*/

                props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
                
		// authentification
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
                                @Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("goodlink.fes@gmail.com", "nathalie40");
				}
			}
		);

		// construct message
		Message message = new MimeMessage(session);
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		message.setSubject(subject);
		message.setContent(content, "text/html; charset=ISO-8859-1");

		// send email
		Transport.send(message);
	}
}
