/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceCalculatrice;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author YASSALIE
 */
@WebService(serviceName = "Calculatrice")
public class Calculatrice {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    @WebMethod(operationName = "additionner")
    public int additionner(@WebParam(name = "a") int a,@WebParam(name = "b") int b){
        return a+b;
    }
    @WebMethod(operationName = "soustraire")
    public int soustraire(@WebParam(name = "a") int a,@WebParam(name = "b") int b){
        return a-b;
    }
    @WebMethod(operationName = "multiplier")
    public int multiplier(@WebParam(name = "a") int a,@WebParam(name = "b") int b){
        return a*b;
    }
    
}
