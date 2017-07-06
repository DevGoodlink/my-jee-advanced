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
public class CreditCard {
    private int num=0;
    private String type;
    
    public CreditCard(){
        
    }
    public CreditCard(int num,String type){
        this.num=num;
        this.type=type;
    }
    public int getNum(){
        return this.num;
    }
    public String getType(){
        return this.type;
    }
    public void setNum(int num){
        this.num=num;
    }
    public void setType(String type){
        this.type=type;
    }
    public boolean equals(CreditCard cc){
        return((this.num==cc.getNum())&&(this.type==cc.getType())) ;
        
    }
    public CreditCard clone(){
        return new CreditCard(this.num,this.type);//this.getNum(), this.getType());
    }
    public boolean valider(int num){
        if(num>999999999){
            if(num<=9999999999L){
                if (num%2==0){
                return true;
                }
            
            }
        }
        return false;
    }
}
