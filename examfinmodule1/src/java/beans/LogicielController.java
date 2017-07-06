/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.LogicielFacade;
import entity.Logiciel;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author YASSALIE
 */
@ManagedBean
@SessionScoped
public class LogicielController implements Serializable {

    public LogicielController() {
        
        
    }
    
    @EJB
    private LogicielFacade  lf;
    
    private Logiciel logiciel=new Logiciel();
    
    private List<Logiciel> logicielList = new ArrayList<Logiciel>();
    
    public String doCreateLogiciel(){
        this.logiciel=lf.add(logiciel);
        this.logicielList=lf.findAll();
        return "ListLogiciel.xhtml";
    }
    
    public String doDeleteLogiciel(){
        lf.delete(logiciel);
        return "ListLogiciel.xhtml";
    }

    public Logiciel getLogiciel() {
        return logiciel;
    }

    public void setLogiciel(Logiciel logiciel) {
        this.logiciel = logiciel;
    }

    public List<Logiciel> getLogicielList() {
        return logicielList;
    }

    public void setLogicielList(List<Logiciel> logicielList) {
        this.logicielList = logicielList;
    }
    
    
}
