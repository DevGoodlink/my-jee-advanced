/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.ws.WebServiceRef;
import ws.CardValidator_Service;
import ws.CreditCard;

/**
 *
 * @author YASSALIE
 */
@Named(value = "controllerMb")
@SessionScoped
public class ControllerMb implements Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/yassalie-pc_8080/ccwsentity/CardValidator.wsdl")
    private CardValidator_Service service;
    private String number;
    private Integer controlNumber;
    private String type;
    private String expiryDate;
    //------------------Pour la table des cartes de crédits
    
    private ArrayList<CreditCard> lstCc= new ArrayList<>();
    private CreditCard cc=new CreditCard();//credit card to remove
    
    
    public ControllerMb() {
     
    }
    
    public ArrayList<CreditCard> getLstCc() {
        return lstCc;
    }

    public void setLstCc(ArrayList<CreditCard> lstCc) {
        this.lstCc = lstCc;
    }
    
    public CreditCard getCc() {
        return cc;
    }

    public void setCc(CreditCard cc) {
        this.cc = cc;
    }
    
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(Integer controlNumber) {
        this.controlNumber = controlNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
    public String addCc(){
        CreditCard cc0= new CreditCard();
        cc0.setControlNumber(controlNumber);
        cc0.setNumber(number);
        cc0.setExpiryDate(expiryDate);
        cc0.setType(type);
        create(cc0);
        lstCc=(ArrayList < CreditCard >)findAll();
        return "tosuccess";
    }
    public String removeCc(){
        remove(cc);
        return "tosuccess";
    }
    private void create(ws.CreditCard entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        
        ws.CardValidator port = service.getCardValidatorPort();
        port.create(entity);
    }

    private java.util.List<ws.CreditCard> findAll() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        ws.CardValidator port = service.getCardValidatorPort();
        return port.findAll();
    }

    private void remove(ws.CreditCard entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        ws.CardValidator port = service.getCardValidatorPort();
        port.remove(entity);
    }
    
    
    
}
