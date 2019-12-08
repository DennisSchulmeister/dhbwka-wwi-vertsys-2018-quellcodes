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

import dhbwka.wwi.vertsys.ws.rest_serien_server.jpa.Series;
import java.util.List;
import javax.ejb.Stateless;

/**
 * Einfache EJB mit den üblichen Methoden zum Lesen und Schreiben von
 * Serien in der Datenbank. Diese Klasse nutzt unsere altbekannte EntityBean
 * aus den JPA-Folien, um einen Grundstock an Standardmethoden anzubieten.
 */
@Stateless
public class SeriesBean extends EntityBean<Series, String> {

    public SeriesBean() {
        super(Series.class);
    }

    /**
     * Suche von Serien anhand eines Suchbegriffs. Der Suchbegriff muss im
     * Name, Genre oder Land-Feld der Serie vorkommen.
     * 
     * @param query Suchbegriff
     * @return Liste mit den gefundenen Serien
     */
    public List<Series> findByQuery(String query) {
        if (query == null || query.trim().isEmpty()) {
            query = "";
        }
        
        query = "%" + query + "%";

        return em.createQuery("SELECT s FROM Series s"
                            + "    WHERE s.title   LIKE :query"
                            + "       OR s.genre   LIKE :query"
                            + "       OR s.country LIKE :query")
                .setParameter("query", query)
                .getResultList();
    }
}
