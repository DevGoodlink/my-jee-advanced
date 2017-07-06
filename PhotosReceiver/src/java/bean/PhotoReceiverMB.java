/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.StreamMessage;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/Tp2Destination")
    ,@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class PhotoReceiverMB implements MessageListener{

    @Inject @JMSConnectionFactory("jms/Tp2ConnectionFactory")
    JMSContext context;
    @Resource(lookup ="jms/Tp2Destination")
    private Queue dataQueue;
    
    @EJB
    private EjbSessionStatless ejbSessionStatless;
    
    public PhotoReceiverMB() {
        
    }
    
    @Override
    public void onMessage(Message arg) {
        try {
            
            System.out.println("Message reçu");
            //if(arg instanceof Message){
            StreamMessage message = (StreamMessage)arg ;//(StreamMessage)arg;
            String photoName = message.getStringProperty("nomPhoto");//.readString();
            int taillePhoto = message.getIntProperty("taille");//readInt();
            byte[] photoData = new byte[taillePhoto];
            message.readBytes(photoData);
            ByteArrayInputStream bias = new ByteArrayInputStream(photoData);
            BufferedImage image = ImageIO.read(bias);
            sendMessage(new String[] {photoName,""+taillePhoto});
            ejbSessionStatless.savePicture(photoName, taillePhoto, image);
            ejbSessionStatless.sendMail(photoName);
            sendMessage(new String[] {"Mail de notification envoyé."});
            /*}else{
                System.out.println(""+arg);
            }*/
        } catch (Exception e) {
            System.out.println("Ereur : "+e.getMessage());
        } 
    }
    public  void sendMessage(String[] args) {
        if(args.length==2)
        context.createProducer().send(dataQueue, "Photo bien reçu : "+args[0] + " taille : "+args[1] + " Octets.");
        else
        context.createProducer().send(dataQueue, args[0]);

    }
}
