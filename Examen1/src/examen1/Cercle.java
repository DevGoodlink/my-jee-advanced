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
public class Cercle extends FigureGeometrique{
    private int centre=0;
    private int rayon=0;
    
    
    public Cercle(int num,String couleur,int centre, int rayon){
        super(num,couleur);
        this.centre=centre;
        this.rayon=rayon;
    }
    public double getSurface(){
        return Math.sqrt(this.rayon) * Math.PI;
    }
    public double getPerimetre(){
        return 2*this.rayon*Math.PI;        
    }
    public int getCentre(){
        return this.centre;
    }
    public int getRayon(){
        return this.rayon;
    }
    public void setCentre(int centre){
        this.centre=centre;
    }
    public void setRayon(int rayon){
        this.rayon=rayon;
    }
    
}
