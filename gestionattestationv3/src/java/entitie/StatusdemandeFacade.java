/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitie;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author YASSALIE
 */
@Stateless
public class StatusdemandeFacade extends AbstractFacade<Statusdemande> {

    @PersistenceContext(unitName = "gestionattestationv3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatusdemandeFacade() {
        super(Statusdemande.class);
    }
    
}
