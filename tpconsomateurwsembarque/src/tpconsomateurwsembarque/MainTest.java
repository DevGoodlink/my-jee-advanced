/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpconsomateurwsembarque;

/**
 *
 * @author YASSALIE
 */
public class MainTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(validate("0021267115"));
    }

    private static boolean validate(java.lang.String arg0) {
        ws.PhoneValidatorService service = new ws.PhoneValidatorService();
        ws.PhoneValidator port = service.getPhoneValidatorPort();
        return port.validate(arg0);
    }
    
}
