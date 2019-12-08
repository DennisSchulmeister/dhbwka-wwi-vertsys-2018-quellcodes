/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.rest_songs_server.ejb;

import dhbwka.wwi.vertsys.ws.rest_songs_server.jpa.Song;
import java.util.List;
import javax.ejb.Stateless;

/**
 * Einfache EJB mit den üblichen Methoden zum Lesen und Schreiben von
 * Songs in der Datenbank. Diese Klasse nutzt unsere altbekannte EntityBean
 * aus den JPA-Folien, um einen Grundstock an Standardmethoden anzubieten.
 */
@Stateless
public class SongBean extends EntityBean<Song, Long> {

    public SongBean() {
        super(Song.class);
    }

    /**
     * Suche von Songs anhand eines Suchbegriffs. Der Suchbegriff muss im
     * Name, Künstler oder Songwriter-Feld des Songs vorkommen.
     * 
     * @param query Suchbegriff
     * @return Liste mit den gefundenen Songs
     */
    public List<Song> findByQuery(String query) {
        if (query == null || query.trim().isEmpty()) {
            query = "";
        }
        
        query = "%" + query + "%";

        return em.createQuery("SELECT s FROM Song s"
                            + "    WHERE s.name        LIKE :query"
                            + "       OR s.artist      LIKE :query"
                            + "       OR s.songwriters LIKE :query")
                .setParameter("query", query)
                .getResultList();
    }
}
