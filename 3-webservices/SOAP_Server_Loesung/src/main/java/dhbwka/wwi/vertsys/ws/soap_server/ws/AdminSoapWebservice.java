/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.soap_server.ws;

import dhbwka.wwi.vertsys.ws.soap_server.ejb.MovieBean;
import dhbwka.wwi.vertsys.ws.soap_server.ejb.UserBean;
import dhbwka.wwi.vertsys.ws.soap_server.jpa.Movie;
import dhbwka.wwi.vertsys.ws.soap_server.jpa.Participant;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * SOAP-Webservice zur Anlage der Testdaten. Sonst ist das Beispiel so leer. :-)
 */
@Stateless
@WebService
public class AdminSoapWebservice {

    @EJB
    private UserBean userBean;
    
    @EJB
    private MovieBean movieBean;

    @WebMethod
    @WebResult(name = "movie")
    public List<Movie> createExampleData(
            @WebParam(name = "username", header = true) String username,
            @WebParam(name = "password", header = true) String password
    ) throws UserBean.InvalidCredentialsException, UserBean.AccessRestrictedException {
        // Berechtigung prüfen
        userBean.validateUser(username, password, "soap-admin");
        
        // Alle alten Einträge löschen
        for (Movie movie : this.movieBean.findAll()) {
            this.movieBean.delete(movie);
        }

        Movie movie;
        List<Participant> participants;

        // 2001: Odyssee im Weltraum (1968)
        participants = new ArrayList<>();
        participants.add(new Participant("Stanley Kubrick", "Drehbuch, Regie"));
        participants.add(new Participant("Arthur C. Clarke", "Drehbuch"));
        participants.add(new Participant("Geoffrey Unsworth", "Kamera"));
        participants.add(new Participant("Aram Khatchaturian", "Musik"));
        participants.add(new Participant("György Ligeti", "Musik"));
        participants.add(new Participant("Johann Strauss", "Musik"));
        participants.add(new Participant("Richard Strauss", "Musik"));
        participants.add(new Participant("Ray Lovejoy", "Schnitt"));
	
        movie = new Movie("2001: Odyssee im Weltraum", 1968, "Der Filmklassiker von Stanley Kubrick", participants);
        this.movieBean.saveNew(movie);

        // Star Trek: Der Film (1979)
        participants = new ArrayList<>();
        participants.add(new Participant("Robert Wise", "Regie"));
        participants.add(new Participant("Harold Livingston", "Drehbuch"));
        participants.add(new Participant("Gene Roddenberry", "Produktion"));
        participants.add(new Participant("Jerry Goldsmith", "Musik"));
        participants.add(new Participant("Richard H. Kline", "Kamera"));
        participants.add(new Participant("Todd Ramsay", "Schnitt"));

        movie = new Movie("Star Trek: Der Film", 1979, "Der erste Star Trek-Film nach dem Ende der Originalserie", participants);
        this.movieBean.saveNew(movie);
        
        // Spaceballs (1987)
        participants = new ArrayList<>();
        participants.add(new Participant("Mel Brooks", "Regie, Drehbuch, Produktion"));
        participants.add(new Participant("Thomas Meehan", "Drehbuch"));
        participants.add(new Participant("Ronny Graham", "Drehbuch"));
        participants.add(new Participant("John Morris", "Musik"));
        participants.add(new Participant("Nick McLean", "Kamera"));
        participants.add(new Participant("Conrad Buff IV", "Schnitt"));

        movie = new Movie("Spaceballs", 1987, "Mel Brooks geniale Filmparodie auf Star Wars", participants);
        this.movieBean.saveNew(movie);
        
        // Gravity (2013)
        participants = new ArrayList<>();
        participants.add(new Participant("Alfonso Cuarón", "Regie, Drehbuch, Schnitt, Produktion"));
        participants.add(new Participant("Jonás Cuarón", "Drehbuch"));
        participants.add(new Participant("George Clooney", "Drehbuch"));
        participants.add(new Participant("David Heyman", "Produktion"));
        participants.add(new Participant("Steven Price", "Musik"));
        participants.add(new Participant("Emmanuel Lubezki", "Kamera"));
        participants.add(new Participant("Mark Sanger", "Schnitt"));
        
        movie = new Movie("Gravity", 2013, "Neuzeitlicher SciFi-Film über die Verschmutzung des Weltraums", participants);
        this.movieBean.saveNew(movie);
        
        // Interstellar (2014)
        participants = new ArrayList<>();
        participants.add(new Participant("Christopher Nolan", "Regie, Drehbuch. Produktion"));
        participants.add(new Participant("Jonathan Nolan", "Drehbuch"));
        participants.add(new Participant("Emma Thomas", "Produktion"));
        participants.add(new Participant("Lynda Obst", "Produktion"));
        participants.add(new Participant("Hans Zimmer", "Musik"));
        participants.add(new Participant("Hoyte van Hoytema", "Kamera"));
        participants.add(new Participant("Lee Smith", "Schnitt"));

        movie = new Movie("Interstellar", 2013, "Zukunftsutopie über die Entwicklung der Menschheit", participants);
        this.movieBean.saveNew(movie);

        // Der Marsianer – Rettet Mark Watney (2015)
        participants = new ArrayList<>();
        participants.add(new Participant("Ridley Scott", "Regie, Produktion"));
        participants.add(new Participant("Drew Goddard", "Drehbuch"));
        participants.add(new Participant("Mark Huffam", "Produktion"));
        participants.add(new Participant("Simon Kinberg", "Produktion"));
        participants.add(new Participant("Michael Schaefer", "Produktion"));
        participants.add(new Participant("Aditya Sood", "Produktion"));
        participants.add(new Participant("Harry Gregson-Williams", "Musik"));
        participants.add(new Participant("Dariusz Wolski", "Kamera"));
        participants.add(new Participant("Pietro Scalia", "Schnitt"));

        movie = new Movie("Der Marsianer – Rettet Mark Watney", 2013, "Unverstellbares Szenario auf dem Mars, ein Astronaut bleibt zurück", participants);
        this.movieBean.saveNew(movie);
        
        // Ergebnis zurückliefern
        return this.movieBean.findAll();
    }
}
