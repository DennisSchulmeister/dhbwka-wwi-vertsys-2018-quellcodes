/*
 * Copyright © 2019 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.junit_beispiel;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Enterprise Java Bean mit Methoden zum Lesen, Anlegen, Aktualisieren und
 * Löschen von Gästebucheinträgen.
 */
@Stateless
public class GuestbookBean {
    
    @PersistenceContext
    protected EntityManager em;
    
    public GuestbookEntity findById(long id) {
        return this.em.find(GuestbookEntity.class, id);
    }
    
    public GuestbookEntity saveNew(GuestbookEntity gb) {
        em.persist(gb);
        return gb;
    }
    
    public GuestbookEntity update(GuestbookEntity gb) {
        return em.merge(gb);
    }
    
    public void delete(GuestbookEntity gb) {
        // Aus irgend einem Grund wirft em.remove(gb) eine Exception, wenn
        // die Methode im Unit Test ausgeführt wird. Diese Version funktioniert
        // aber genauso gut, so dass der Test erfolgreich durchläuft.
        em.createQuery("DELETE FROM GuestbookEntity gb WHERE gb.id = :id")
          .setParameter("id", gb.getId())
          .executeUpdate();
    }
}
