/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex1collection;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author YASSALIE
 */
public class Ex1Collection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LinkedList<String> l=new LinkedList<String>();
        
        System.out.println("Liste en A : "+l.toString());
        l.add("a");l.add("b");
        System.out.println("Liste en B : "+l.toString());
        ListIterator<String> it=l.listIterator();
        it.next();
        it.add("c");it.add("b");
        System.out.println("Liste en C : "+l.toString());
        it = l.listIterator();
        it.next();it.add("b");it.add("d");
        System.out.println("Liste en D : "+l.toString());
        
        it = l.listIterator(l.size());
        while (it.hasPrevious()){
            String ch=it.previous();
            if (ch.equals("b")){
                it.remove();
                break;
            }
        }
        System.out.println("Liste en E : "+l.toString());
        it=l.listIterator();
        it.next();it.next();
        it.set("x");
        System.out.println("Liste en F : "+l.toString());
        
        
        
        
    }
    
}
