/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author YASSALIE
 */
@Entity
@PrimaryKeyJoinColumn(name = "ID")

public class Camion extends Vehicule implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private int ptc;
    private String type;

    public int getPtc() {
        return ptc;
    }

    public void setPtc(int ptc) {
        this.ptc = ptc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
   
    
}
