/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.beans;

import entitylayer.Attestation;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author YASSALIE
 */
@Stateless
public class AttestationFacade extends AbstractFacade<Attestation> {

    @PersistenceContext(unitName = "attestation-managementPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AttestationFacade() {
        super(Attestation.class);
    }
    
}
