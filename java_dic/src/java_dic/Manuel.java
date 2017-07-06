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
public class Manuel extends Livre {
    private int niveauScolaire=0;
    
    public Manuel(int num,String titre,String auteur,int nbrPage,int niveauScolaire){
        super(num,titre,auteur,nbrPage);
        this.niveauScolaire=niveauScolaire;
    }
    
    public String toString(){
        return "\n "+super.toString()+" manuel niveau scolaire : " + this.niveauScolaire;
    }
}
