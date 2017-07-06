/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_dic;

public class Dictionnaire extends Document{
    private int langue=0;
    static final int ARABE=1;
    static final int FRANCAIS=2;
    static final int ANGLAIS=3;
    
    public Dictionnaire(int num,String titre, int langue){
        super(num,titre);
        this.langue=langue;
    }
    public String toString(){
        return super.toString()+" dictionnaire langue : "+ this.langue;
    }
}
