/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex2collection;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author YASSALIE
 */
public class Ex2Collection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        List<String> a=new LinkedList<String>();
        List<String> b=new LinkedList<String>();
        b.add("f");b.add("so");b.add("wi");b.add("in");
        a.add("h");a.add("r");a.add("sa");
        ListIterator<String> aIter=a.listIterator();
        
        Iterator<String> bIter=b.iterator();
       
        while(bIter.hasNext()){
            if(aIter.hasNext())aIter.next();
            aIter.add(bIter.next());
        }
        System.out.println(a.toString());
    }
    
}
