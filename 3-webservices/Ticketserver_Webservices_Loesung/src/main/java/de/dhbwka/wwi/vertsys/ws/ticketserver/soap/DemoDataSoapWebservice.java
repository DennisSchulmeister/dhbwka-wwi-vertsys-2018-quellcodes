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

import de.dhbwka.wwi.vertsys.ws.ticketserver.ejb.DemoDataBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * SOAP-Webservice zur Anlage der Demodaten.
 */
@Stateless
@WebService
public class DemoDataSoapWebservice {
    
    @EJB
    private DemoDataBean demoDataBean;
    
    @WebMethod
    @WebResult(name = "message")
    public String createDemoData() {
        this.demoDataBean.createDemoData();
        return "Demodaten wurden angelegt";
    }
}
