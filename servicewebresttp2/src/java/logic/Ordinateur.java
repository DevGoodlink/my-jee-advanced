/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author YASSALIE
 */
@XmlRootElement
public class Ordinateur {
    private Long id;
    private String marque;
    private float prix;

    public Ordinateur() {
    }

    public Ordinateur(Long id, String marque, float prix) {
        this.id = id;
        this.marque = marque;
        this.prix = prix;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Ordinateur{" + "id=" + id + ", marque=" + marque + ", prix=" + prix + '}';
    }
}
