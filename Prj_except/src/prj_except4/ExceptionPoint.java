/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prj_except4;


public class ExceptionPoint extends Exception {
    int info=9;
    public String getTypeException(){
        return "Il s'agit d'une exception de type ExceptionPoint";
    }
}
