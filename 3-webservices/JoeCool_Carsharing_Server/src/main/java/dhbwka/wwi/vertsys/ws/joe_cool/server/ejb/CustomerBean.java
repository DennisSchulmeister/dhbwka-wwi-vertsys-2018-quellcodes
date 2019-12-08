/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.joe_cool.server.ejb;

import dhbwka.wwi.vertsys.ws.joe_cool.server.jpa.Customer;
import javax.ejb.Stateless;

/**
 * EJB zum Lesen und Schreiben von Kundendaten.
 */
@Stateless
public class CustomerBean extends EntityBean<Customer, Long> {
    
    public CustomerBean() {
        super(Customer.class);
    }
}
