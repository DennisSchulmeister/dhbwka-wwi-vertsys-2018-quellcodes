/*
 * Copyright © 2019 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative C    //<editor-fold defaultstate="collapsed" desc="Anlage von Demodaten">ommons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.ticket2rock_server.ejb;

import dhbwka.wwi.vertsys.javaee.ticket2rock_interfaces.entity.Event;
import dhbwka.wwi.vertsys.javaee.ticket2rock_interfaces.entity.Ticket;
import dhbwka.wwi.vertsys.javaee.ticket2rock_interfaces.remote.EventNotFoundException;
import dhbwka.wwi.vertsys.javaee.ticket2rock_interfaces.remote.TicketServiceRemote;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Enterprise Java Bean, die das Remote Interface "TicketServiceRemote" aus dem
 * Projekt "Ticket2Rock_Interfaces" implementiert und somit den Service zur
 * Verfügung stellt. Die Methoden dieser Klasse werden von der Clientanwendung
 * per entferntem Methodenaufruf aufgerufen.
 * 
 * Dieser Service bietet eine simple Möglichkeit zum Verkauf von Konzertkarten,
 * Um das Beispiel einfach zu halten, wurde der Service fachliche nicht zu Ende
 * modelliert, sondern nur sehr rudimentär ausprogrammiert.
 */
@Stateless
public class TicketService implements TicketServiceRemote {

    @PersistenceContext
    EntityManager em;
    
    /**
     * Automatische Anlage von Demodaten, falls keine Veranstaltungen in der
     * Datenbank vorhanden sind. Dies ist KEINE Remote-Methode.
     */
    @Override
    public void createDemoData() {
        Event event;
        LocalDateTime dateTime;
        
        List<Event> events = em.createQuery("SELECT e FROM Event e").getResultList();
        if (!events.isEmpty()) return;
        
        dateTime = LocalDateTime.now().plusWeeks(4).withHour(20).withMinute(0);
        event = new Event("An Evening with Mark Knopfler & Band", "SAP Arena, Mannheim", dateTime);
        em.persist(event);
        
        dateTime = LocalDateTime.now().plusWeeks(8).withHour(19).withMinute(30);
        event = new Event("Elton John Live in Concert", "Schleyer-Halle, Stuttgart", dateTime);
        em.persist(event);
        
        dateTime = LocalDateTime.now().plusWeeks(10).withHour(19).withMinute(00);
        event = new Event("Steve Hacket: The Night Siren Tour", "Schleyer-Halle, Stuttgart", dateTime);
        em.persist(event);
    }
    
    /**
     * Remote-Methode zum Abruf einer Liste mit allen Veranstaltungen.
     * @return Liste aller Veranstaltungen
     */
    @Override
    public List<Event> getAllEvents() {
        return em.createQuery("SELECT e FROM Event e").getResultList();
    }

    /**
     * Remote-Methode zum Kaufen von Tickets.
     * 
     * @param eventId Gesuchte Veranstaltung
     * @param owner Name des Käufers
     * @param amount Anzahl der zu kaufenden Tickets
     * @return Liste der gekauften Tickets
     * @throws EventNotFoundException Veranstaltung nicht gefunden
     */
    @Override
    public List<Ticket> buyTickets(long eventId, String owner, int amount) throws EventNotFoundException {
        List<Ticket> tickets = new ArrayList<>();
        
        // Veranstaltung suchen
        Event event = em.find(Event.class, eventId);
        
        if (event == null) {
            throw new EventNotFoundException("Die gesuchte Veranstaltung wurde nicht gefunden.");
        }
        
        // Tickets kaufen
        for (int i = 0; i < amount; i++) {
            Ticket ticket = new Ticket(event, owner);
            em.persist(ticket);
            tickets.add(em.merge(ticket));
        }
        
        return tickets;
    }
    
}
