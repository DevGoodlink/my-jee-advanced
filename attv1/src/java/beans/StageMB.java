/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author YASSALIE
 */
@Named(value = "stageMB")
@SessionScoped
public class StageMB implements Serializable {
    private String Code;
    private String mail;
    private String checkCaptcha;
    
    public StageMB() {
    }
    public String generatedCaptcha(){
        int a0,a1,a2,a3;
        Random generator = new Random();
        a0=generator.nextInt(256);a1=generator.nextInt(256);
        a2=generator.nextInt(256);a3=generator.nextInt(256);
        return "As488";
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
}
