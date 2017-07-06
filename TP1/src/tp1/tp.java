import java.applet.Applet;
import java.awt.Graphics;


public class tp extends Applet{
	
	private String msg;
	public void init(){
		//msg="Bonjour";
	}
	public void paint(Graphics g){
		//g.drawString(msg,50,50);
            g.drawRect(10, 20, 30, 100);
	}
}