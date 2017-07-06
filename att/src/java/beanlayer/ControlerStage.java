/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanlayer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author YASSALIE
 */
@ManagedBean
@SessionScoped
public class ControlerStage {

    /**
     * Creates a new instance of ControlerStage
     */
    private boolean checkedCaptcha=false; 
    private String mail="";
    private String code="";
    public ControlerStage() 
    {
        
    }

    public boolean isCheckedCaptcha() {
        return checkedCaptcha;
    }

    public void setCheckedCaptcha(boolean checkedCaptcha) {
        this.checkedCaptcha = checkedCaptcha;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
}
