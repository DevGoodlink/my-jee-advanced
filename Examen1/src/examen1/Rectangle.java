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
public class Rectangle extends FigureGeometrique{
    
    private int largeur=0;
    private int hauteur=0;
    
    public Rectangle(int num,String couleur,int largeur,int hauteur){
        super(num,couleur);
        this.largeur=largeur;
        this.hauteur=hauteur;
    }
    
    public int getLargeur(){
        return this.largeur;
    }
    public int getHauteur(){
        return this.hauteur;
    }
    public void setLargeur(int largeur){
        this.largeur=largeur;
    }
    public void setHauteur(int hauteur){
        this.hauteur=hauteur;
    }
    public double getSurface(){
        return this.hauteur+this.largeur;
    }
    public double getPerimetre(){
        return 2*(this.hauteur+this.largeur);
    }
}
