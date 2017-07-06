/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogicallayer;

import javax.ejb.Local;

/**
 *
 * @author YASSALIE
 */
@Local
public interface CalculatorEJBLocal {

    int add(int a, int b);
    
}
