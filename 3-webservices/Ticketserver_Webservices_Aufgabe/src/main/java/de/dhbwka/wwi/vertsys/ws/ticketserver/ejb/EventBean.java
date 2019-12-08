/*
 * Copyright © 2019 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package de.dhbwka.wwi.vertsys.ws.ticketserver.ejb;

import de.dhbwka.wwi.vertsys.ws.ticketserver.jpa.Event;
import java.util.List;
import javax.ejb.Stateless;

/**
 * Enterprise Java Bean zur Verwaltung von Veranstaltungen.
 */
@Stateless
public class EventBean extends EntityBean<Event, Long> {
    
    public EventBean() {
        super(Event.class);
    }
    
    public List<Event> searchEvents(String query) {
        query = "%" + query + "%";
        
        return em.createQuery("SELECT e FROM Event e"
                            + "  WHERE e.artist LIKE :query"
                            + "     OR e.location LIKE :query")
                 .setParameter("query", query)
                 .getResultList();
    }
}
