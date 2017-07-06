/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.util.Calendar;

/**
 *
 * @author YASSALIE
 */
public class Etudiant {
    private int num;
    private String nom;
    private String prenom;
    private String adr;
    private String dateNaiss;
    
    public Etudiant(){
        
    }
    public Etudiant(int num, String nom , String prenom , String adr,String datenaiss){
        this.num=num;
        this.nom=nom;
        this.prenom=prenom;
        this.adr=adr;
        this.dateNaiss=datenaiss;
       }

    public int getNum() {
        return num;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdr() {
        return adr;
    }

    public String getDateNaiss() {
        return dateNaiss;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public void setDateNaiss(String dateNaiss) {
        this.dateNaiss = dateNaiss;
    }
    
    public int getAge(){
        String annee=this.dateNaiss.substring(6);
        int year=Integer.parseInt(annee);
        int yearencours = Calendar.getInstance().get(Calendar.YEAR);
        return yearencours-year;
        }
    public boolean isSameAge(Etudiant etu){
        if (this.getAge()!=etu.getAge())
            return false;
        else
            return true;
    }
}
