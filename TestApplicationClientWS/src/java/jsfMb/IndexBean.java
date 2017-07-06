/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfMb;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author YASSALIE
 */
@Named(value = "indexBean")
@SessionScoped
public class IndexBean implements Serializable {

    /**
     * Creates a new instance of IndexBean
     */
    public IndexBean() {
    }
    
}
