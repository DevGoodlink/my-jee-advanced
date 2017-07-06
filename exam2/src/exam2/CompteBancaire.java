/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam2;

/**
 *
 * @author YASSALIE
 */
public class CompteBancaire {
   private int num=0;
   private String adresse;
   private Personne p;
    
    public CompteBancaire(int num, String adresse,Personne p){
        this.num=num;
        this.adresse=adresse;
        this.p=p;
    }
    public String toString(){
        return ("Compte n° "+this.num+"\n adresse :"+
                this.adresse+" personne : "+this.p.toString());
    }
    
}
