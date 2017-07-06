/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_dic;

/**
 *
 * @author YASSALIE
 */
public class Livre extends Document{
    private String auteur;
    private int nbrPage;
   
    public Livre(int num,String titre,String auteur,int nbrPage){
        super(num,titre);
        this.auteur=auteur;
        this.nbrPage=nbrPage;
    }
    public String getAuteur(){
        return this.auteur;
    }
    public String toString(){
        return "\n "+super.toString()+" livre auteur : "+this.auteur+" nbr page : "+this.nbrPage;
    }
}
