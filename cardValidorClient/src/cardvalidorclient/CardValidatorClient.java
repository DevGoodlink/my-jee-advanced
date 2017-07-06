/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardvalidorclient;

import ma.ac.upf.CardValidator;
import ma.ac.upf.CardValidator_Service;
import ma.ac.upf.CreditCard;

/**
 *
 * @author YASSALIE
 */
public class CardValidatorClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CardValidator_Service service = new CardValidator_Service();
        CardValidator cv = service.getCardValidatorPort();
        
        CreditCard creditCard = new CreditCard();
        creditCard.setNumber("22222");
        creditCard.setControlNumber(123);
        creditCard.setExpiryDate("15/12/2017");
        creditCard.setType("Visa");
        
        if(cv.validate(creditCard))System.out.println("la card de crédit "+creditCard.getNumber()+" est valide");
        
    }
    
}
