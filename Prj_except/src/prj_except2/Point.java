/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prj_except2;

/**
 *
 * @author YASSALIE
 */
class Point {
    private int x,y;
    
    public Point(int x, int y) throws ExceptionConstruction {
    if ((x<0)||(y<0))throw new ExceptionConstruction();
    //la machine virtuelle est passé en argument au bloc Catch
    this.x=x;
    this.y=y;
    }
    public void affiche(){
        System.out.println("coordonnées : " + x +" / "+y);
    }
    
}
