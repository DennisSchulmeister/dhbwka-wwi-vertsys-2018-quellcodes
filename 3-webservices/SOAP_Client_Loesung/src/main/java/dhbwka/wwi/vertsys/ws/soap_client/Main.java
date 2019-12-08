/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.soap_client;

import dhbwka.wwi.vertsys.ws.soap_server.ws.Movie;
import dhbwka.wwi.vertsys.ws.soap_server.ws.MovieSoapWebservice;
import dhbwka.wwi.vertsys.ws.soap_server.ws.MovieSoapWebserviceService;
import java.util.List;

/**
 * Mini-Beispiel zum Aufruf eines SOAP-Webservices. Damit das funktioniert, muss
 * im Hintergrund das Projekt "SOAP_Server_Beispiel" ausgeführt werden.
 */
public class Main {

    public static void main(String[] args) {
        // Stub-Objekt zum entfernten Aufruf erstellen
        MovieSoapWebserviceService service = new MovieSoapWebserviceService();
        MovieSoapWebservice movieWs = service.getMovieSoapWebservicePort();

        // Webservice-Operation "findAll" aufrufen
        List<Movie> allMovies = movieWs.findAll();

        // Abgerufenes Ergebnis anzeigen
        System.out.println("========================");
        System.out.println("Die große SOAP-Videothek");
        System.out.println("========================");
        System.out.println();
        
        for (Movie movie : allMovies) {
            System.out.println("Name:         " + movie.getName());
            System.out.println("Beschreibung: " + movie.getDescription());
            System.out.println("Jahr:         " + movie.getReleaseYear());
            System.out.println();
        }
    }
}
