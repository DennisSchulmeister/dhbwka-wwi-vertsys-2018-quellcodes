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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Beim Start der Anwendung ein paar Veranstaltungen anlegen, damit wir
 * ausreichend Demodaten zur Verfügung haben.
 */
@Stateless
public class DemoDataBean {

    @EJB
    private EventBean eventBean;

    @EJB
    private TicketBean ticketBean;

    public void createDemoData() {
        for (Event event : this.eventBean.findAll()) {
            this.eventBean.delete(event);
        }

        for (Ticket ticket : this.ticketBean.findAll()) {
            this.ticketBean.delete(ticket);
        }

        String[] artists = {"Steve Hacket", "Phil Collins", "Queen", "Mark Knopfler", "Elton John", "Lady Gaga", "Madonna"};
        String[] locations = {"Mannheim", "Stuttgart", "Karlsruhe", "München", "Frankfurt", "Berlin", "Hamburg"};
        String[] times = {"19:00:00", "19:30:00", "20:00:00", "20:15:00"};
        int maxSeats = 250;
        int timeSpan = 180;
        int maxLocations = 5;
        int maxEventsPerLocation = 3;

        for (String artist : artists) {
            // Veranstaltungsorte aussuchen
            int numLocations = (int) Math.ceil(Math.random() * maxLocations);
            List<String> pickedLocations = new ArrayList<>();

            while (numLocations > 0) {
                int i = (int) Math.floor(Math.random() * locations.length);
                String location = locations[i];

                if (pickedLocations.contains(location)) {
                    continue;
                }

                pickedLocations.add(location);
                numLocations--;
            }

            // Veranstaltungen speichern
            for (String location : pickedLocations) {
                int numEvents = (int) Math.ceil(Math.random() * maxEventsPerLocation);

                for (int i = 0; i < numEvents; i++) {
                    int dateShift = (int) Math.ceil(Math.random() * timeSpan);
                    LocalDate date = LocalDate.now().plus(dateShift, ChronoUnit.DAYS);
                    int timeIndex = (int) Math.floor(Math.random() * times.length);
                    LocalTime time = LocalTime.parse(times[timeIndex]);
                    LocalDateTime dateTime = LocalDateTime.of(date, time);
                    
                    int amountTickets = (int) Math.ceil(Math.random() * maxSeats);

                    Event event = new Event();
                    event.setArtist(artist);
                    event.setLocation(location);
                    event.setStartDate(Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant()));
                    event.setMaxTickets(amountTickets);
                    event.setRemainingTickets(amountTickets);
                    eventBean.saveNew(event);
                }
            }
        }
    }

}
