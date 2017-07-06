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
public class Roman extends Livre {
    private int prix=0;
    public static int NEANT=0;
    public static int GONCOURT=1;
    public static int MEDICIS=2;
    public static int INTERALLIE=3;
    
    public Roman(int num,String titre,String auteur,int nbrPage,int prix){
        super(num,titre,auteur,nbrPage);
        this.prix=prix;
    }
    public String toString(){
        return super.toString()+"\n Roman prix : "+this.prix;
    }
}
