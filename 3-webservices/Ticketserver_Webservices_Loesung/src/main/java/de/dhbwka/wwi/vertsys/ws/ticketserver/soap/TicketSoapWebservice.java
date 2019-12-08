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

import de.dhbwka.wwi.vertsys.ws.ticketserver.ejb.TicketBean;
import de.dhbwka.wwi.vertsys.ws.ticketserver.jpa.Ticket;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * SOAP-Webservice zum Kaufen von Tickets.
 */
@Stateless
@WebService
public class TicketSoapWebservice {

    @EJB
    private TicketBean ticketBean;

    @WebMethod
    @WebResult(name = "ticket")
    public Ticket findTicketById(@WebParam(name = "id") long id) {
        return this.ticketBean.findById(id);
    }

    @WebMethod
    @WebResult(name = "ticket")
    public List<Ticket> buyTicketsbuyTickets(
            @WebParam(name = "eventId") Long eventId,
            @WebParam(name = "amount") int amount,
            @WebParam(name = "ticketHolderName") String ticketHolderName,
            @WebParam(name = "ticketHolderAdress") String ticketHolderAddress
    ) throws TicketBean.UnknownEventException, TicketBean.EventSoldOutException {

        return this.ticketBean.buyTickets(eventId, amount, ticketHolderName, ticketHolderAddress);
    }
}
