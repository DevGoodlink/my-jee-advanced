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

/**
 *
 * @author YASSALIE
 */
@Entity
public class Engineer extends Employee implements Serializable {

    private static final long serialVersionUID = 1L;
   
    private String status;
    private int nbrProject;
   
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNbrProject() {
        return nbrProject;
    }

    public void setNbrProject(int nbrProject) {
        this.nbrProject = nbrProject;
    }
    
    
    
}
