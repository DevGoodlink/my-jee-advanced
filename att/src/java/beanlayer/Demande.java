/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanlayer;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author YASSALIE
 */
@ManagedBean
@SessionScoped
public class Demande {
private int id;
private Date dde;
private int etat;
private int userid;
    public Demande() 
    {
        
    }
    public void createDemande(){
        
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDde() {
        return dde;
    }

    public void setDde(Date dde) {
        this.dde = dde;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    
}
