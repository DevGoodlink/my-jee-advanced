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
public class Personne {
    private int idBancaire=0;
    private String nomComplet;
    
    public Personne(int idBancaire,String nomComplet){
        this.idBancaire=idBancaire;
        this.nomComplet=nomComplet;
    }
    public String toString(){
        return ("idbancaire n° : "+this.idBancaire +" Nom Complet : "+this.nomComplet);
    }
    
    

}
