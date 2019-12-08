/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.generische_entity_ejb.beispiel;

import dhbwka.wwi.vertsys.javaee.generische_entity_ejb.EntityBean;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Test für eines generische Entity Bean.
 */
@Stateless
public class FilmBean extends EntityBean<Film, Long> {

    @PersistenceContext
    EntityManager em;

    public FilmBean() {
        super(Film.class);
    }
    
    //
    // Zusätzliche Selektionsmethoden
    //
    public List<Film> findByJahr(int jahr) {
        return em.createQuery("SELECT f FROM Film f WHERE f.jahr = :jahr")
                 .setParameter("jahr", jahr)
                 .getResultList();
    }

}
