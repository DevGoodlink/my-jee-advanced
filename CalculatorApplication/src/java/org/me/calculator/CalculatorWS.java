/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.calculator;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService
public class CalculatorWS {

    @WebMethod
    public String operation(@WebParam(name="param_name")String param) {
        return param;
    }
    @WebMethod
    public int add(@WebParam(name = "a") int a,@WebParam(name = "b") int b) {
        return a+b;
    }
}
