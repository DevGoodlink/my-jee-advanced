/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import tools.MailTool;

/**
 *
 * @author YASSALIE
 */
@Stateless
public class EjbSessionStatless {

    public void savePicture(String nomPhoto, int taillePhoto,BufferedImage photo ) throws FileNotFoundException, IOException {
        File f = new File("photos/"+nomPhoto);
        Iterator<ImageReader> readers = ImageIO.getImageReaders(f);
        String imageFormat ="";
        if(readers.hasNext()){
            ImageReader reader = readers.next();
            imageFormat = reader.getFormatName();
        }
        ImageIO.write(photo,imageFormat,f);
        System.out.println("Image sauvegardée sur le disque");
    }

    public void sendMail(String fileName) {
        MailTool.sendMail(fileName);
        
    }
}
