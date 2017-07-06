/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class TP1 extends JFrame{
    private JButton b1=null;
    private JTextField t=null;
    public TP1(){
        super("Exemple Applet 16-02-2016");
        this.setBounds(10,10,800,400);//deux premiers entiers pour défnir la position horizontale et verticale et les autres pour la largeur et la hauteur
        this.setLayout(null);//pour maitriser le positionnement
        this.setVisible(true);
        b1 = new JButton();
        b1.setText("ok");
        b1.setBounds(50, 50, 100 , 30);
        this.add(b1);
        b1.setVisible(true);
        
        
        t= new JTextField();
        t.setBounds(50, 100, 100 , 30);
        t.setText("vide");
        this.add(t);
        t.setVisible(true);
        b1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
               t.setText("Hello World");
            }
        });
        WindowAdapter win;
        win = new WindowAdapter() {
            public void WindowClosing(WindowEvent e){
                System.exit(0);
            }
        };
        this.addWindowListener(win);
    }
    public static void main(String[] args) {
        // TODO code application logic here
    new TP1();
    
    }
    
}
