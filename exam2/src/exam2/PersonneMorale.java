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
public class PersonneMorale extends Personne{
    private String activite;
    
    public PersonneMorale(int idBancaire,String nomComplet,String activite){
        super(idBancaire,nomComplet);
        this.activite=activite;
    }
    public String toString(){
        return ("Peronne Morale : "+this.activite+" ---> "+super.toString());
    }
}
