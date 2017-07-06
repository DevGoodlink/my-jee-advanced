/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FenetreEcouteur;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author YASSALIE
 */
public class Fen extends JFrame{

    private JButton monBouton1, monBouton2;

    public Fen() {
        setTitle("Avec deux boutons");
        setSize(300,200);
        monBouton1 = new JButton("Bouton 1");
        monBouton2 = new JButton("Bouton 2");
        Container contenu = getContentPane();
        contenu.setLayout(new FlowLayout());
        contenu.add(monBouton1);
        contenu.add(monBouton2);
        EcouteBouton1 ecout1 = new EcouteBouton1();
        EcouteBouton2 ecout2 = new EcouteBouton2();
        monBouton1.addActionListener(ecout1);
        monBouton2.addActionListener(ecout2);
    }
}
