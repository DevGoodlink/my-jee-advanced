/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpatter.observer.meteo;

import java.util.Observable;

/**
 *
 * @author Imad
 */
public class DonneesMeteo extends Observable{
    
    private float temmperature;
    private float himidite;
    private float pression;

    public DonneesMeteo() {
    }

    void measurmentsChanged(){
        setChanged();
        notifyObservers();
    }
    public float getTemmperature() {
        return temmperature;
    }

    public void setTemmperature(float temmperature) {
        this.temmperature = temmperature;
    }

    public float getHimidite() {
        return himidite;
    }

    public void setHimidite(float himidite) {
        this.himidite = himidite;
    }

    public float getPression() {
        return pression;
    }

    public void setPression(float pression) {
        this.pression = pression;
    }
    
    
}
