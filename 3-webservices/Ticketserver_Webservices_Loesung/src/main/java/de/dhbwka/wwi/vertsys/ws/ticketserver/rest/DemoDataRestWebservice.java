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

import de.dhbwka.wwi.vertsys.ws.ticketserver.ejb.DemoDataBean;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * REST-Webservice zur Anlage der Demodaten.
 */
@Path("CreateDemoData")
@Consumes("application/json")
@Produces("application/json")
public class DemoDataRestWebservice {
    
    @EJB
    private DemoDataBean demoDataBean;
    
    public static class Response {
        
        public String status = "";
        public String message = "";
    }
    
    @GET
    public Response createDemoData() {
        this.demoDataBean.createDemoData();
        
        Response response = new Response();
        response.status = "OK";
        response.message = "Demodaten wurden angelegt";
        return response;
    }
}
