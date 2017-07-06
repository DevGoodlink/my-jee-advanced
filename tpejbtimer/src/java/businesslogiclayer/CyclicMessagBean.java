/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogiclayer;

import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class CyclicMessagBean {
    @Schedule(hour="*", minute = "*/1")
    public void showMessage() {
        System.out.println("Une minute vient de sécouler");
    }
}
