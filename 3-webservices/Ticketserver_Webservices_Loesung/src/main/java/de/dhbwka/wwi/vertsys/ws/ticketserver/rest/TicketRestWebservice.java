/*
 * Copyright © 2019 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package de.dhbwka.wwi.vertsys.ws.ticketserver.rest;

import de.dhbwka.wwi.vertsys.ws.ticketserver.ejb.TicketBean;
import de.dhbwka.wwi.vertsys.ws.ticketserver.jpa.Ticket;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * REST-Webservice zum Kaufen von Tickets.
 */
@Path("Tickets")
@Consumes("application/json")
@Produces("application/json")
public class TicketRestWebservice {

    @EJB
    private TicketBean ticketBean;

    public static class BuyRequest {

        public long eventId = 0;
        public int amount = 0;

        @Column(length = 100)
        @Size(min = 1, max = 64, message = "Das Feld „Name des Ticketbesitzers” muss zwischein einem und 100 Zeichen lang sein.")
        @NotNull(message = "Das Feld „Name des Ticketbesitzers” darf nicht leer sein.")
        public String ticketHolderName = "";

        @Column(length = 100)
        @Size(min = 1, max = 64, message = "Das Feld „Adresse des Ticketbesitzers” muss zwischein einem und 100 Zeichen lang sein.")
        @NotNull(message = "Das Feld „Adresse des Ticketbesitzers” darf nicht leer sein.")
        public String ticketHolderAddress = "";
    }

    @POST
    public List<Ticket> buyTicketsbuyTickets(@Valid BuyRequest request)
            throws TicketBean.UnknownEventException, TicketBean.EventSoldOutException {

        return this.ticketBean.buyTickets(request.eventId, request.amount, request.ticketHolderName, request.ticketHolderAddress);
    }

    @GET
    @Path("{id}")
    public Ticket findTicketById(@PathParam("id") long id) {
        return this.ticketBean.findById(id);
    }
}
