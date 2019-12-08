/*
 * Copyright © 2019 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.ticket2rock_interfaces.remote;

import dhbwka.wwi.vertsys.javaee.ticket2rock_interfaces.entity.Event;
import dhbwka.wwi.vertsys.javaee.ticket2rock_interfaces.entity.Ticket;
import java.util.List;
import javax.ejb.Remote;

/**
 * Remote Interface für einen entfernten Ticketservice. Der Service kann eine
 * Liste von Veranstaltungen zurückliefern und Tickets zu einer Veranstaltung
 * verkaufen.
 * 
 * Um das Beispiel einfach zu halten, ist der Service nicht in allen Details zu
 * Ende modelliert. Es soll nur demonstriert werden, wie eine EJB Methoden
 * anbieten kann, die von entfernten Clients per entferntem Methodenaufruf
 * genutzt werden können.
 */
@Remote
public interface TicketServiceRemote {
    /**
     * Demodaten in der Datenbank anlegen, falls nicht vorhanden
     */
    void createDemoData();
    
    /**
     * @return Liste mit allen Veranstaltungen
     */
    List<Event> getAllEvents();
    
    /**
     * Verkauf eines Tickets.
     * 
     * @param eventId Gesuchte Veranstaltung
     * @param owner Name des Käufers
     * @param amount Anzahl der zu kaufenden Tickets
     * @return Liste der gekauften Tickets
     * @throws EventNotFoundException Veranstaltung nicht gefunden
     */
    List<Ticket> buyTickets(long eventId, String owner, int amount) throws EventNotFoundException;
}
