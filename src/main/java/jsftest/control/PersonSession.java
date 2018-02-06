/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsftest.control;

import jsftest.exception.UniquePersonException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import jsftest.entity.Person;

/**
 *
 * @author Vano.Beridze
 */
@Stateless
public class PersonSession {
    
    @PersistenceContext(unitName = "jsftest-PU")
    EntityManager em;
    
    public void createPerson(Person person) throws UniquePersonException {
        try {
            em.persist(person);
            em.flush();//without this line the exception is not thrown
        } catch (PersistenceException pe) {
            throw new UniquePersonException();
        }
    }
    
}
