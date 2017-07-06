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
public class BibTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Roman r=new Roman(1,"Baldasard", "Amine", 400,1);
        Dictionnaire d=new Dictionnaire(1, "Larousse", 2);
        Revue re=new Revue(1, "Sc et vie", 1, 2015);
        Manuel m=new Manuel(1, "Java", "Nfaoui", 60, 3);
        System.out.println("Roman " + r.toString());
        System.out.println("Dictionnaire : " + d.toString());
        System.out.println("Revue : "+re.toString());
        System.out.println("Manuel : "+m.toString());
        System.out.println("------------------------------bib1--------------");
        Bibliotheque bib1=new Bibliotheque(5, 3, "Fes");
        bib1.ajouter(r);
        bib1.ajouter(d);
        bib1.ajouter(re);
        bib1.ajouter(m);
        System.out.println("nombre doc : "+ bib1.getNbrdoc());
        System.out.println(bib1.toString());
        System.out.println("Les auteurs : "+bib1.getAuteurs());
    }
    
}
