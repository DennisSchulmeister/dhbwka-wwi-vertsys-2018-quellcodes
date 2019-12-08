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

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;

/**
 * Einfache Enterprise Java Beans zum Zugriff auf Beziehungsdaten. Diese EJB ist
 * kein Webservice, wird aber vom Webservice verwenden.
 */
@Stateless
public class BeziehungBean extends EntityBean<Beziehung, Long> {
    
    public BeziehungBean() {
        super(Beziehung.class);
    }
    
    public List<Beziehung> findByPerson(Person person) {
        return this.em.createQuery("SELECT b FROM Beziehung b WHERE b.person1 = :person OR b.person2 = :person ORDER BY b.seitWann")
                      .setParameter("person", person)
                      .getResultList();
    }
    
    public List<Beziehung> findByDatumActive(Date datum) {
        return this.em.createQuery("SELECT b FROM Beziehung b WHERE b.seitWann <= :datum AND b.bisWann >= :datum ORDER BY b.seitWann")
                      .setParameter("datum", datum)
                      .getResultList();
    }
    
}
