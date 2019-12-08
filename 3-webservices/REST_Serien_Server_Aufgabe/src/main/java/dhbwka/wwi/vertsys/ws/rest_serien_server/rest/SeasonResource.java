/*
 * Copyright © 2019 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.rest_serien_server.rest;

import dhbwka.wwi.vertsys.ws.rest_serien_server.ejb.SeasonBean;
import dhbwka.wwi.vertsys.ws.rest_serien_server.ejb.SeriesBean;
import javax.ejb.EJB;

/**
 * REST-Webservice für Staffeln. Hier können Staffeln abgerufen, angelegt und
 * gelöscht werden.
 */
public class SeasonResource {

    @EJB
    private SeriesBean seriesBean;

    @EJB
    private SeasonBean seasonBean;

    // TODO: Webservice ausprogrammieren
    // =================================
    //
    //   - GET    /Series/{seriesId}/Season/           → Abruf aller Staffeln der gewünschten Serie
    //   - POST   /Series/{seriesId}/Season/           → Anlegen einer neuen Staffel zur gewünschten Serie
    //
    //   - GET    /Series/{seriesId}/Season/{seasonNr} → Abruf einer bestimmten Staffel der gewünschten Serie
    //   - PUT    /Series/{seriesId}/Season/{seasonNr} → Aktualisieren einer bestimmten Staffel der gewünschten Serie
    //   - DELETE /Series/{seriesId}/Season/{seasonNr} → Löschen einer bestimmten Staffel der gewünschten Serie
    //
    // Der Webservice soll sowohl JSON- als XML-Daten empfangen und senden können.
}
