/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpatter.observer.meteo;

/**
 *
 * @author Imad
 */
public interface Observateur {
     void Acctualiser(float temperature, float humidite,float pression);
}
