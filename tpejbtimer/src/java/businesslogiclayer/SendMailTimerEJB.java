/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogiclayer;

import javax.ejb.Stateless;

import javax.ejb.Schedule;
import tool.MailSenderTool;

/**
 *
 * @author YASSALIE
 */
@Stateless
public class SendMailTimerEJB {
    @Schedule(hour="*", minute = "*/1")
    private void sendMail(){
        try {
            System.out.println("Message à envoyer");
            MailSenderTool.sendMail();
            System.out.println("Message envoyé avec succés");
        } catch (Exception ex) {
            ex.getMessage();
        }

    }
    
}
