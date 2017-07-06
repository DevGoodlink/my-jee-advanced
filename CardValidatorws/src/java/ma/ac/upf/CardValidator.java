/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.ac.upf;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author YASSALIE
 */
@WebService(serviceName = "CardValidator")
public class CardValidator {

    public CardValidator() {
    }

    @WebMethod(operationName = "validate")
    public boolean validate(@WebParam(name = "creditCard") CreditCard cc) {
        int number = Integer.parseInt(cc.getNumber());
        if(number%2==0) return true;
        return false;
    }
}
