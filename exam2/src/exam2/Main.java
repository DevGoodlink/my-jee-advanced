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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*CompteBancaire[] tabComptes=new CompteBancaire[3];
        PersonnePhysique pp1=new PersonnePhysique(1, "Alaoui Mrani", "Z303522");
        PersonneMorale pm1=new PersonneMorale(2, "CapreMaroc", "Concerves");
        CompteBancaire cb1=new CompteBancaire(1, "Fes1302", pp1);
        tabComptes[0]=cb1;
        System.out.println(pm1.toString());
        System.out.println(pp1.toString());
        System.out.println(tabComptes[0].toString());*/
        CreditCard cc1=new CreditCard(1111111122,"visa");
        CreditCard cc2=new CreditCard(1111111124,"Master");
        CreditCard cc3=cc1.clone();
        //System.out.println(cc1.getNum()%2);
        System.out.print(cc1.valider(cc3.getNum()));
        
        int i=00001111;
        
        String s= Integer.toString(i);
        
        System.out.println(s);
        
        
    }
    
}
