/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.beans;

import entitylayer.Stage;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author YASSALIE
 */
@Stateless
public class StageFacade extends AbstractFacade<Stage> {

    @PersistenceContext(unitName = "attestation-managementPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StageFacade() {
        super(Stage.class);
    }
    
}
