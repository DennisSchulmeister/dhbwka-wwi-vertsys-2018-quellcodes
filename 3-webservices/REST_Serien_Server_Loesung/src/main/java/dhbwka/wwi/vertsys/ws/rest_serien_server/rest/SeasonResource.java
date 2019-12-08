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
import dhbwka.wwi.vertsys.ws.rest_serien_server.jpa.Season;
import dhbwka.wwi.vertsys.ws.rest_serien_server.jpa.SeasonId;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * REST-Webservice für Staffeln. Hier können Staffeln abgerufen, angelegt und
 * gelöscht werden.
 */
@Path("Series/{seriesId}/Season")
@Consumes({"application/json", "text/xml"})
@Produces({"application/json", "text/xml"})
public class SeasonResource {

    @EJB
    private SeriesBean seriesBean;

    @EJB
    private SeasonBean seasonBean;

    /**
     * GET /api/Series/{seriesId}/Season/
     *
     * Auslesen aller Staffeln einer Serie.
     *
     * @param seriesId ID der Serie
     * @return Liste der gefundenen Staffeln
     */
    @GET
    public List<Season> findAllSeasonsForSeries(@PathParam("seriesId") String seriesId) {
        return this.seasonBean.findBySeriesId(seriesId);
    }
    
    /**
     * POST /api/Series/{seriesId}/Season/
     * 
     * Neue Staffel zu einer Serie anlegen.
     * 
     * @param season Zu speichernde Staffel
     * @return Gespeicherte Staffel
     * @throws ConstraintViolationException 
     */
    @POST
    public Season saveNewSeason(@Valid Season season) throws ConstraintViolationException {
        return this.seasonBean.saveNew(season);
    }

    /**
     * GET /api/Series/{seriesId}/Season/{seasonId}/
     *
     * Auslesen einer einzelnen Staffel anhand ihrer ID.
     *
     * @param seriesId Gesuchte Serie
     * @param seasonNr Gesuchte Staffel
     * @return Gesuchte Staffel oder null
     */
    @Path("{seasonNr}")
    @GET
    public Season getSeason(
            @PathParam("seriesId") String seriesId,
            @PathParam("seasonNr") int seasonNr
    ) {
        SeasonId seasonId = new SeasonId(seriesId, seasonNr);
        return this.seasonBean.findById(seasonId);
    }

    /**
     * PUT /api/Series/{seriesId}/Season/{seasonId}/
     *
     * Aktualisieren einer vorhandenen Staffel.
     *
     * @param seriesId Gesuchte Serie
     * @param seasonNr Gesuchte Staffel
     * @param season Zu aktualisierende Staffel
     * @return Gespeicherte Staffel
     */
    @Path("{seasonNr}")
    @PUT
    public Season updateSeason(
            @PathParam("seriesId") String seriesId,
            @PathParam("seasonNr") int seasonNr,
            @Valid Season season
    ) throws ConstraintViolationException {
        season.setSeries(this.seriesBean.findById(seriesId));
        season.setSeasonNumber(seasonNr);
        return this.seasonBean.update(season);
    }

    /**
     * DELETE /api/Series/{seriesId}/Season/{seasonId}/
     *
     * Löschen einer vorhandenen Staffel.
     *
     * @param seriesId Gesuchte Serie
     * @param seasonNr Gesuchte Staffel
     * @return Gelöschte Staffel
     */
    @Path("{seasonNr}")
    @DELETE
    public Season deleteSeason(
            @PathParam("seriesId") String seriesId,
            @PathParam("seasonNr") int seasonNr
    ) {
        SeasonId seasonId = new SeasonId(seriesId, seasonNr);
        Season season = this.seasonBean.findById(seasonId);

        if (season != null) {
            this.seasonBean.delete(season);
        }

        return season;
    }

}
