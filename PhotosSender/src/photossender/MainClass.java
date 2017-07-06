/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photossender;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainClass extends JFrame{
        private JLabel label=new JLabel("Choisir une photo de la liste : ");
        private JComboBox<String> combo;
        private JButton btnSend = new JButton("Envoyer");
        private JPanel panelPrincipal;
        private PanelPhoto panelPhoto=new PanelPhoto();
        
    public MainClass(){
        this.setTitle("Photo sender by jms");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new FlowLayout());
        panelPrincipal.add(label);
        File file = new File("photos");
        String[] photos = file.list();
        combo=new JComboBox<>(photos);
        panelPrincipal.add(combo);
        panelPrincipal.add(btnSend);
        this.add(panelPrincipal,BorderLayout.NORTH);
        this.add(panelPhoto,BorderLayout.CENTER);
        this.setBounds(10, 10, 800,600);
        this.setVisible(true);
        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String photo = (String) combo.getSelectedItem();
                    File file = new File("photos/"+photo);
                    BufferedImage bi = ImageIO.read(file);
                    panelPhoto.setBi(bi);
                    panelPhoto.repaint();
                    System.out.println(""+file.getAbsolutePath());
                } catch (IOException ex) {
                    Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JMSSender.send((String) combo.getSelectedItem());
                } catch (Exception ex) {
                    ex.printStackTrace();
                } 

            }
        });
                
    }
    
    public static void main(String[] args) {
       new MainClass();
    }
    
}
