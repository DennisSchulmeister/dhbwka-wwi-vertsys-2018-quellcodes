/*
 * Copyright © 2019 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.ticket2rock_client;

import dhbwka.wwi.vertsys.javaee.ticket2rock_interfaces.entity.Event;
import dhbwka.wwi.vertsys.javaee.ticket2rock_interfaces.entity.Ticket;
import dhbwka.wwi.vertsys.javaee.ticket2rock_interfaces.remote.EventNotFoundException;
import dhbwka.wwi.vertsys.javaee.ticket2rock_interfaces.remote.TicketServiceRemote;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Minimale Client-Anwendung, welche die entfernten Methoden des TicketService
 * auf dem Server aufruft.
 */
public class Main {

    public static void main(String[] args) throws NamingException, IOException {
        System.out.println("==================");
        System.out.println("Ticket2Rock Client");
        System.out.println("==================");
        System.out.println("");

        // Entfernte Referenz auf den TicketService aus dem Namensdienst besorgen
        InitialContext ctx = new InitialContext();
        TicketServiceRemote ticketService = (TicketServiceRemote) ctx.lookup("Ticket2Rock_Server/TicketServiceRemote");

        if (ticketService == null) {
            System.err.println("FEHLER: Entfernte Referenz im Namensdienst nicht gefunden!");
            return;
        }
        
        // Demodaten anlegen
        ticketService.createDemoData();

        // Abruf aller vorhandenen Events vom Server
        System.out.println("Verfügbare Events");
        System.out.println("-----------------");
        System.out.println("");

        List<Event> events = ticketService.getAllEvents();  // Entfernter Aufruf

        for (Event event : events) {
            System.out.println("Event ID:      " + event.getId());
            System.out.println("Bezeichnung:   " + event.getName());
            System.out.println("Datum/Uhrzeit: " + event.getDateTime());
            System.out.println("");
        }

        System.out.println();

        // Tickets kaufen
        System.out.println("Tickets kaufen");
        System.out.println("--------------");
        System.out.println("");

        BufferedReader fromKeyboard = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Event ID: ");
        long eventId = Long.parseLong(fromKeyboard.readLine());

        System.out.print("Anzahl: ");
        int amount = Integer.parseInt(fromKeyboard.readLine());

        System.out.print("Name des Käufers: ");
        String owner = fromKeyboard.readLine();

        System.out.println("");

        try {
            // Entfernter Aufruf
            List<Ticket> tickets = ticketService.buyTickets(eventId, owner, amount);

            System.out.println("Gekaufte Tickets");
            System.out.println("----------------");
            System.out.println("");

            for (Ticket ticket : tickets) {
                System.out.println("Ticket ID: " + ticket.getId());
                System.out.println("Event:     " + ticket.getEvent().getName() + ", " + ticket.getEvent().getDateTime());
                System.out.println("Käufer:    " + ticket.getOwner());
                System.out.println();
            }
        } catch (EventNotFoundException ex) {
            System.err.println("FEHLER: " + ex);
        }
    }
}
