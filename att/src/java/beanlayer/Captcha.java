/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanlayer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Captcha {
    {
        captcha = generate(5);
    }
    /**
     * Creates a new instance of Captcha
     */
    String captcha;
    String input;

    
    public Captcha() 
    {
    }
    
    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
    
    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String val) {
        this.captcha = val;
    }
    public boolean verify(){
        
        return (captcha.equalsIgnoreCase(input))? true : false;
        
    }
    public String generate(int length)
    {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; // Tu supprimes les lettres dont tu ne veux pas
        String pass = "";
        for(int x=0;x<length;x++)
        {
           int i = (int)Math.floor(Math.random() * 62); // Si tu supprimes des lettres tu diminues ce nb
           pass += chars.charAt(i);
        }
        //System.out.println(pass);
        return pass;
    }
    
}
