/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.jws.WebService;

@WebService
public class PhoneValidator {

    public PhoneValidator() {
    }
    
    public boolean validate(String phoneNumber){
        return (phoneNumber.length()>10);
    }
    
}
