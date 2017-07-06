/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogicallayer;

import javax.ejb.Remote;

/**
 *
 * @author YASSALIE
 */
@Remote
public interface CalculatorEJBRemote {

    int mul(int a, int j);
    
}
