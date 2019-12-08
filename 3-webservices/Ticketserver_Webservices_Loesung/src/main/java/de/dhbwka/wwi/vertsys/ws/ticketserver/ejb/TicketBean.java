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
import de.dhbwka.wwi.vertsys.ws.ticketserver.jpa.Ticket;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Enterprise Java Bean zur Verwaltung von Veranstaltungen.
 */
@Stateless
public class TicketBean extends EntityBean<Ticket, Long> {

    @PersistenceContext
    protected EntityManager em;

    public TicketBean() {
        super(Ticket.class);
    }
    
    /**
     * Kauf von Tickets für eine Veranstaltung.
     *
     * @param eventId Event-Id
     * @param amount Anzahl zu kaufender Tickets
     * @param ticketHolderName Name des Käufers
     * @param ticketHolderAddress Adresse des Käufers
     * @return Liste der gekauften Tickets
     * @throws TicketBean.UnknownEventException Event nicht gefunden
     * @throws TicketBean.EventSoldOutException Nicht genügend freie Plätze
     */
    public List<Ticket> buyTickets(Long eventId, int amount, String ticketHolderName, String ticketHolderAddress)
            throws UnknownEventException, EventSoldOutException {

        // Event suchen
        Event event = this.em.find(Event.class, eventId);

        if (event == null) {
            throw new UnknownEventException("Die gesuchte Veranstaltung wurde nicht gefunden.");
        }

        // Verfügbare Anzahl entsprechend der gekauften Tickets herbsetzen
        if (event.getRemainingTickets() < amount) {
            throw new EventSoldOutException("Leider sind nur noch " + event.getRemainingTickets() + " Tickets verfügbar.");
        }

        event.setRemainingTickets(event.getRemainingTickets() - amount);
        this.em.merge(event);

        // Gekaufte Tickets generieren und speichern
        List<Ticket> tickets = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            Ticket ticket = new Ticket();
            ticket.setEvent(event);
            ticket.setTicketHolderName(ticketHolderName);
            ticket.setTicketHolderAdress(ticketHolderAddress);
            
            ticket = this.em.merge(ticket);
            tickets.add(ticket);
        }

        return tickets;
    }

    /**
     * Fehler: Event nicht gefunden.
     */
    public static class UnknownEventException extends Exception {

        public UnknownEventException(String message) {
            super(message);
        }
    }

    /**
     * Fehler: Nicht genügend freie Plätze.
     */
    public static class EventSoldOutException extends Exception {

        public EventSoldOutException(String message) {
            super(message);
        }
    }
}
