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

import dhbwka.wwi.vertsys.ws.rest_songs_server.ejb.SongBean;
import dhbwka.wwi.vertsys.ws.rest_songs_server.jpa.Song;
import java.util.List;
import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST-Webservice zur Verwaltung von Songs.
 */
@Path("Songs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SongResource {

    @EJB
    SongBean songBean;

    /**
     * GET /api/Songs/
     *
     * GET /api/Songs/?query=…
     *
     * Auslesen einer Liste von Musikstücken. Optional kann an die URL der
     * Parameter ?query=… angehängt werden, um die Liste auf bestimmte Stücke
     * passend zum übergebenen Suchbegriff zu begrenzen.
     *
     * @param query Suchbegriff
     * @return Eine Liste mit allen gefundenen Stücken
     */
    @GET
    public List<Song> findSongs(@QueryParam("query") @DefaultValue("") String query) {        
        return this.songBean.findByQuery(query);
    }

    /**
     * POST /api/Songs/
     *
     * Speichern eines neuen Songs.Der Song muss im JSON-Format an den Server
     * geschickt werden.
     *
     * @param song Zu speichernder Song
     * @return Gespeicherte Songdaten
     */
    @POST
    public Song saveNewSong(@Valid Song song) {
        return this.songBean.saveNew(song);
    }

    /**
     * GET /api/Songs/{id}/
     *
     * Auslesen eines einzelnen Songs anhand seiner ID.
     *
     * @param id ID des gesuchten Songs
     * @return Gefundener Song
     */
    @Path("{id}")
    @GET
    public Song getSong(@PathParam("id") long id) {
        return this.songBean.findById(id);
    }

    /**
     * PUT /api/Songs/{id}/
     *
     * Aktualisieren eines vorhandenen Songs.Der Song muss im JSON-Format an den
     * Server geschickt werden.
     *
     * @param song Zu speichernder Song
     * @return Gespeicherte Songdaten
     */
    @Path("{id}")
    @PUT
    public Song Response(@Valid Song song) {
        return this.songBean.update(song);
    }

    /**
     * DELETE /api/Songs/{id}/
     *
     * Löschen eines vorhandenen Songs.
     *
     * @param id ID des zu löschenden Songs
     * @return Daten des gelöschten Songs
     */
    @Path("{id}")
    @DELETE
    public Song deleteSong(@PathParam("id") long id) {
        Song song = this.songBean.findById(id);

        if (song != null) {
            this.songBean.delete(song);
        }

        return song;
    }

}
