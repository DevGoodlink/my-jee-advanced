/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photossender;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author YASSALIE
 */
public class PanelPhoto extends JPanel{
    private BufferedImage bi;
    
    public void paint(Graphics g){
        g.drawImage(bi, 0,0,this.getWidth(), this.getHeight(), null);
    }

    public BufferedImage getBi() {
        return bi;
    }

    public void setBi(BufferedImage bi) {
        this.bi = bi;
    }
    
    
}
