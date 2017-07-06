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
public class Bibliotheque {
    private int capacite;
    private int nbreSalle;
    private int nbrDocs;
    private String adr;
    private Document docs[];
    
    public Bibliotheque(int capacite,int nbreSalle,String adr){
        this.docs=new Document[capacite];
        this.nbreSalle=nbreSalle;
        this.adr=adr;
        this.nbrDocs=0;
    }
    
    public boolean ajouter(Document doc){
       
       if (this.nbrDocs<docs.length){
           this.docs[this.nbrDocs++]=doc;
           return true;
       }else return false;
       
    }
    public int getNbrdoc(){
        return this.nbrDocs;
    }
    public String getAuteurs(){
        String chaine="";
        for (Document doc:docs){
            if (doc!=null){
                if (doc instanceof Livre){
                    Livre l=(Livre)doc;
                    chaine="\n "+l.getAuteur();
                }
            }
        }
        return chaine;
    }
    public String toString(){
        String chaine1="";
        chaine1="[bibliotheque : Adresse: "+this.adr+" Nbre Salle : "+this.nbreSalle;
        //String chaine2="";
        for(int i=0;i<this.nbrDocs;i++){//Document doc:docs){
            chaine1=chaine1+""+ docs[i].toString();
        }
        return chaine1;//+"\n"+chaine2;
    }
}
