/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 *
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 *
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.rest_songs_server.rest;

import dhbwka.wwi.vertsys.ws.rest_songs_server.jpa.Song;
import dhbwka.wwi.vertsys.ws.rest_songs_server.ejb.SongBean;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Minimaler REST-Webservice, der eine Reihe von Demodaten für uns anlegt.
 */
@Path("CreateDemoData")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CreateDemoData {

    @EJB
    SongBean musicPieceBean;

    @GET
    public StatusResponse createDemoData() {
        for (Song piece : this.musicPieceBean.findAll()) {
            this.musicPieceBean.delete(piece);
        }

        Song piece;

        piece = new Song("Goodbye Yellow Brick Road", "Elton John", "Bernie Taupin", 1973);
        this.musicPieceBean.saveNew(piece);

        piece = new Song("Candle In The Wind", "Elton John", "Bernie Taupin", 1973);
        this.musicPieceBean.saveNew(piece);

        piece = new Song("Blue Wonderful", "Elton John", "Bernie Taupin", 2016);
        this.musicPieceBean.saveNew(piece);

        piece = new Song("Brothers In Arms", "Dire Straits", "Mark Knopfler", 1985);
        this.musicPieceBean.saveNew(piece);

        piece = new Song("Sultans of Swing", "Dire Straits", "Mark Knopfler", 1978);
        this.musicPieceBean.saveNew(piece);

        piece = new Song("Calling Elvis", "Dire Straits", "Mark Knopfler", 1991);
        this.musicPieceBean.saveNew(piece);

        piece = new Song("Tequila Sunrise", "The Eagles", "Don Henley, Glenn Frey", 1973);
        this.musicPieceBean.saveNew(piece);

        piece = new Song("Busy Being Fabulous", "The Eagles", "Don Henley, Glenn Frey", 2007);
        this.musicPieceBean.saveNew(piece);

        piece = new Song("Walking On The Milkiway", "O.M.D.", "McCluskey, Nigel Ipinson, Keith Small", 1996);
        this.musicPieceBean.saveNew(piece);

        piece = new Song("I Want To Break Free", "Queen", "John Deacon", 1984);
        this.musicPieceBean.saveNew(piece);

        // Statusmeldung zurückgeben
        StatusResponse response = new StatusResponse();
        response.status = "OK";
        response.message = "Demodaten wurden angelegt.";
        return response;
    }

}
