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
public abstract class Document{
    private int num;
    private String titre;
    
    public Document(int num,String titre){
        this.num=num;
        this.titre=titre;
    }
    public int getNum(){
        return this.num ;
    }
    public String toString(){
        return "Document numero :"+this.num+ " titre : "+this.titre;
    }
    
}