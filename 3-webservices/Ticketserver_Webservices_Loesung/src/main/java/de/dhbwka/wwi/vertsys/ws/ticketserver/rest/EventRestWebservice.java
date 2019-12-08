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

import de.dhbwka.wwi.vertsys.ws.ticketserver.ejb.EventBean;
import de.dhbwka.wwi.vertsys.ws.ticketserver.jpa.Event;
import java.util.List;
import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * REST-Webservice zur Verwaltung von Veranstaltungen.
 */
@Path("Events")
@Consumes("application/json")
@Produces("application/json")
public class EventRestWebservice {

    @EJB
    private EventBean eventBean;

    @GET
    public List<Event> searchEvents(@QueryParam("query") @DefaultValue("") String query) {
        return this.eventBean.searchEvents(query);
    }

    @POST
    public Event saveNewEvent(@Valid Event event) {
        return this.eventBean.saveNew(event);
    }

    @GET
    @Path("{id}")
    public Event findEventById(@PathParam("id") long id) {
        return this.eventBean.findById(id);
    }

    @PUT
    @Path("{id}")
    public Event updateEvent(@PathParam("id") long id, @Valid Event event) {
        event.setId(id);
        return this.eventBean.update(event);
    }

    @DELETE
    @Path("{id}")
    public Event deleteEvent(@PathParam("id") long id) {
        Event event = this.eventBean.findById(id);

        if (event != null) {
            this.eventBean.delete(event);
        }

        return event;
    }
}
