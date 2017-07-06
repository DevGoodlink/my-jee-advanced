/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webLayer;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author YASSALIE
 */
@ManagedBean
@RequestScoped
public class BeanOperationController {
    double op1,op2;
    /**
     * Creates a new instance of BeanOperationController
     */
    public BeanOperationController() {
    }
    

    public double getOp1() {
        return op1;
    }

    public void setOp1(double op1) {
        this.op1 = op1;
    }

    public double getOp2() {
        return op2;
    }

    public void setOp2(double op2) {
        this.op2 = op2;
    }
    
    public double doSomme(){
        return op1+op2;
    }
    public double doDifference(){
        return op1-op2;
    }
    /*facultative les actions sont définis dans le fichiers de navigation
    *
    */
    
    
    public String getSomme(){
        return "plus";
    }
    public String getDifference(){
        return "moins";
    }
    
}
