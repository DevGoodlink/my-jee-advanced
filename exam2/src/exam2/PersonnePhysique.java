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
public class PersonnePhysique extends Personne {
    private String cni;
    
    
    public PersonnePhysique(int idBancaire,String nomComplet,String cni){
        super(idBancaire,nomComplet);
        this.cni=cni;
    }
    public String toString(){
        return "Cni : " + this.cni +  "Personne Physique : -->" +super.toString() ;
    }
}
