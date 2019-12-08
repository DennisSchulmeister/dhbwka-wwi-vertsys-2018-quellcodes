/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.soap_server.ejb;

import dhbwka.wwi.vertsys.ws.soap_server.jpa.Movie;
import java.util.List;
import javax.ejb.Stateless;

/**
 * Einfache Enterprise Java Bean zur Verwaltung von Filmen in der Filmdatenbank.
 * Diese Bean ist nicht der Webservice, sondern beinhaltet die Implementierung,
 * auf die der Webservice intern zurückgreift.
 */
@Stateless
public class MovieBean extends EntityBean<Movie, Long> {
    
    public MovieBean() {
        super(Movie.class);
    }
    
    /**
     * Suche mit exakter Übereinstimmung des Namen
     * @param name Gesuchter Name
     * @return Gefundene Filme
     */
    public List<Movie> findByName(String name) {
        return this.em.createQuery("SELECT m FROM Movie m WHERE m.name = :name ORDER BY m.name")
                      .setParameter("name", name)
                      .getResultList();
    }
    
    /**
     * Suche nach Filmen, in deren Namen der gesuchte Begriff vorkommt
     * @param name Gesuchter Name
     * @return Gefundene Filme
     */
    public List<Movie> findByNameContains(String name) {
        name = "%" + name + "%";
        
        return this.em.createQuery("SELECT m FROM Movie m WHERE m.name LIKE :name ORDER BY m.name")
                      .setParameter("name", name)
                      .getResultList();
    }
    
    // Alle anderen Methoden werden von EntityBean geerbt.
    
}
