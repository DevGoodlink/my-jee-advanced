/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weblayer.managedbeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author YASSALIE
 */

public class NumberManagedBean implements Serializable {
    private int valeur;
    private int nombreAdeviner;
    private String messageTentative;
    private String messageFinale;
    private int tentative;
    /**
     * Creates a new instance of NumberManagedBean
     */
    public NumberManagedBean() {
        valeur=0;
        nombreAdeviner=(int)Math.round(Math.random()*10);
        tentative=0;
        messageTentative="";
        messageFinale="";
    }

    public int getValeur() {
        return valeur;
    }

    public int getNombreAdeviner() {
        return nombreAdeviner;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public void setNombreAdeviner(int nombreAdeviner) {
        this.nombreAdeviner = nombreAdeviner;
    }

    public void setMessageTentative(String messageTentative) {
        this.messageTentative = messageTentative;
    }

    public void setMessageFinale(String messageFinale) {
        this.messageFinale = messageFinale;
    }

    public void setTentative(int tentative) {
        this.tentative = tentative;
    }

    public String getMessageTentative() {
        return messageTentative;
    }

    public String getMessageFinale() {
        return messageFinale;
    }

    public int getTentative() {
        return tentative;
    }
    
    public void doVerification(){
        tentative++;
        boolean test=nombreAdeviner==valeur;
        if (test){
            messageFinale="Bravo vous avez bien deviné le bon nombre.";
        }else{
            messageTentative=tentative+" Tentative";
            messageFinale="Non, ce n'est pas le bon chiffre, refaites un essai.";
        }
        if (tentative==0 || test){
            messageTentative="";
        }
        else
        {
            messageTentative="Le nombre à rechercher (aléatoire) est plus "+(nombreAdeviner>valeur?"grand":"petit");
        }
        
    }
    
}
