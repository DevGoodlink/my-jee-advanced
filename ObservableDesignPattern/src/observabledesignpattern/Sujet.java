/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observabledesignpattern;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author YASSALIE
 */
public abstract class Sujet {
    protected List<Observateur> observateurs = new ArrayList<>();
    
    public void ajouterObservateur(Observateur o){
        observateurs.add(o);
    }
    public void supprimerObservateur(Observateur o){
        int i = observateurs.indexOf(o);
        if(i>=0)
            observateurs.remove(i);
    }
    public void notifierObservateurs(){
        observateurs.forEach((o) -> {
            o.actualiser();
        });
    }
}
