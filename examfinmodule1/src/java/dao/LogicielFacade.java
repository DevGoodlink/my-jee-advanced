/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import entity.Logiciel;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
/**
 *
 * @author YASSALIE
 */
@Stateless
public class LogicielFacade {
    @PersistenceContext(unitName = "parcPU")
    private EntityManager em;
    
    public Logiciel add(Logiciel l){
        em.persist(l);
        return l;
    }
    public void delete(Logiciel l){
        em.remove(l);
    }
    public List<Logiciel> findAll(){
        TypedQuery<Logiciel> query = em.createNamedQuery("findAllLogiciel", Logiciel.class);
        return query.getResultList();    
    }

}
