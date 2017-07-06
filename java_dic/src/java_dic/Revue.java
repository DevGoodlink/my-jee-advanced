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
public class Revue extends Document{
    private int mois=0;
    private int annee=0;
    public Revue(int num,String titre,int mois,int annee){
        super(num,titre);
        this.mois=mois;
        this.annee=annee;
    }
    public String toString(){
        return super.toString()+" revue mois : "+this.mois+" annee: "+this.annee;
    }
}
