/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author YASSALIE
 */
@ManagedBean
@SessionScoped
public class ControllerFiliere implements Serializable {

    private long id;
    private String libelle;
    
    public ControllerFiliere() {
    }
    
}
