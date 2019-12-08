/*
 * Copyright © 2019 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package de.dhbwka.wwi.vertsys.ws.ticketserver.soap;

import de.dhbwka.wwi.vertsys.ws.ticketserver.ejb.EventBean;
import de.dhbwka.wwi.vertsys.ws.ticketserver.jpa.Event;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.validation.Valid;

/**
 * SOAP-Webservice zur Verwaltung von Veranstaltungen.
 */
@Stateless
@WebService
public class EventSoapWebservice {

    @EJB
    private EventBean eventBean;

    @WebMethod
    @WebResult(name = "event")
    public List<Event> searchEvents(@WebParam(name = "query") String query) {
        return this.eventBean.searchEvents(query);
    }

    @WebMethod
    @WebResult(name = "event")
    public Event findEventById(@WebParam(name = "id") long id) {
        return this.eventBean.findById(id);
    }

    @WebMethod
    @WebResult(name = "event")
    public Event saveNewEvent(@WebParam(name = "event") @Valid Event event) {
        return this.eventBean.saveNew(event);
    }

    @WebMethod
    @WebResult(name = "event")
    public Event updateEvent(@WebParam(name = "event") @Valid Event event) {
        return this.eventBean.update(event);
    }

    @WebMethod
    @WebResult(name = "event")
    public Event deleteEvent(@WebParam(name = "id") long id) {
        Event event = this.eventBean.findById(id);

        if (event != null) {
            this.eventBean.delete(event);
        }

        return event;
    }
}
