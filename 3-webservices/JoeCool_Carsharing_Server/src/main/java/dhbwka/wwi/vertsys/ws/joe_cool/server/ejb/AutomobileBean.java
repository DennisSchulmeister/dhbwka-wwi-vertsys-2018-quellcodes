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

import dhbwka.wwi.vertsys.ws.joe_cool.server.jpa.Automobile;
import javax.ejb.Stateless;

/**
 * EJB zum Lesen und Schreiben von Fahrzeugdaten.
 */
@Stateless
public class AutomobileBean extends EntityBean<Automobile, Long> {
    
    public AutomobileBean() {
        super(Automobile.class);
    }
}
