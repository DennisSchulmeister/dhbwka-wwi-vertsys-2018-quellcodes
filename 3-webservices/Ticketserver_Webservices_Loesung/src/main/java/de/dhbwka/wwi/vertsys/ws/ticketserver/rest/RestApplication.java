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

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Hauptklasse des REST-Webservices. Hier müssen die Resourcenklassen
 * hinzugefügt werden, damit sie aufgerufen werden können.
 */
@ApplicationPath("api")
public class RestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        
        // Hier die REST-Klassen hinzufügen
        resources.add(DemoDataRestWebservice.class);
        resources.add(EventRestWebservice.class);
        resources.add(TicketRestWebservice.class);
        
        return resources;
    }
    
}
