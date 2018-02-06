/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsftest.mbean;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import jsftest.control.PersonSession;
import jsftest.entity.Person;
import jsftest.exception.UniquePersonException;

/**
 *
 * @author Vano.Beridze
 */
@Named(value = "person")
@SessionScoped
public class PersonBean implements Serializable {
    
    @Inject
    PersonSession session;
    
    private List<Person> persons;
    
    private Person person;

    public PersonBean() {
        person = new Person();
    }
    
    public void createPerson() {
        try {
            session.createPerson(person);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Person Created Successfully", null));
        } catch (UniquePersonException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Duplicate Person Name", null));
        }
    }
    
    /**
     * @return the persons
     */
    public List<Person> getPersons() {
        return persons;
    }

    /**
     * @param persons the persons to set
     */
    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    /**
     * @return the person
     */
    public Person getPerson() {
        return person;
    }

    /**
     * @param person the person to set
     */
    public void setPerson(Person person) {
        this.person = person;
    }
    
}
