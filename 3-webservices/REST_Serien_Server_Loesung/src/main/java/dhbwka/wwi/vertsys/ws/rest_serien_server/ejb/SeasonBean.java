/*
 * Copyright © 2019 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.rest_serien_server.ejb;

import dhbwka.wwi.vertsys.ws.rest_serien_server.jpa.Season;
import dhbwka.wwi.vertsys.ws.rest_serien_server.jpa.SeasonId;
import java.util.List;
import javax.ejb.Stateless;

/**
 * Einfache EJB mit den üblichen Methoden zum Lesen und Schreiben von
 * Staffeln in der Datenbank. Diese Klasse nutzt unsere altbekannte EntityBean
 * aus den JPA-Folien, um einen Grundstock an Standardmethoden anzubieten.
 */
@Stateless
public class SeasonBean extends EntityBean<Season, SeasonId> {

    public SeasonBean() {
        super(Season.class);
    }
    
    /**
     * Suche alle Staffeln einer Serie.
     * 
     * @param seriesId Gesuchte Serie
     * @return Liste mit den Staffeln der Serie
     */
    public List<Season> findBySeriesId(String seriesId) {
        return em.createQuery("SELECT s FROM Season s"
                            + "    WHERE s.series.id = :seriesId"
                            + "    ORDER BY s.seasonNumber")
                 .setParameter("seriesId", seriesId)
                 .getResultList();
    }
    
}
