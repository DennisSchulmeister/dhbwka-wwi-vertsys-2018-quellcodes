/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.seifenoper;

import java.util.List;
import javax.ejb.Stateless;

/**
 * Einfache Enterprise Java Beans zum Zugriff auf Personen. Diese EJB ist kein
 * Webservice, wird aber vom Webservice verwenden.
 */
@Stateless
public class PersonBean extends EntityBean<Person, Long> {
    
    public PersonBean() {
        super(Person.class);
    }
    
    public List<Person> findByNameContains(String name) {
        name = "%" + name + "%";
        
        return this.em.createQuery("SELECT p FROM Person p WHERE p.name LIKE :name ORDER BY p.name")
                      .setParameter("name", name)
                      .getResultList();
    }
    
    public List<Person> findByGeschlecht(Geschlecht geschlecht) {
        return this.em.createQuery("SELECT p FROM Person p WHERE p.geschlecht = :geschlecht ORDER BY p.name")
                      .setParameter("geschlecht", geschlecht)
                      .getResultList();
    }
    
}
