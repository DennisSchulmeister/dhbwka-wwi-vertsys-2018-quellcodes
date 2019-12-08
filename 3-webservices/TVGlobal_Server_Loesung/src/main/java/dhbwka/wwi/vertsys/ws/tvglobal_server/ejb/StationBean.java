/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.tvglobal_server.ejb;

import dhbwka.wwi.vertsys.ws.tvglobal_server.jpa.Station;
import javax.ejb.Stateless;

/**
 * Minimale EJB zum Lesen und Schreiben von Fernsehsendern.
 */
@Stateless
public class StationBean extends EntityBean<Station, String> {

    public StationBean() {
        super(Station.class);
    }

}
