/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen1;

/**
 *
 * @author YASSALIE
 */
public class Main{


    public static void main(String[] args){

        Cercle c1=new Cercle(1, "rouge", 1, 3);
        Cercle c2=new Cercle(2,"vert", 2, 4);
        Rectangle r1=new Rectangle(3, "blanc", 5, 7);
        FigureGeometrique tabFigures[] = new FigureGeometrique[3];
        //System.out.println("Bonjour"); 
        tabFigures[0]=c1;
        tabFigures[1]=c2;
        tabFigures[2]=r1;
        for (FigureGeometrique figure:tabFigures){
            if (figure instanceof Cercle){
                Cercle c= (Cercle)figure;
                
                System.out.println("la surface du cercle "+c.getCentre()+" "+c.getRayon()+" "+c.getSurface());
                System.out.println("le périmètre du cercle "+c.getPerimetre());
                
            }else {
                Rectangle r=(Rectangle)figure;
                System.out.println("la surface du rectangle "+r.getSurface());
                System.out.println("le périmètre du rectangle "+r.getPerimetre());   
            }
        }
    }

}
     
   
