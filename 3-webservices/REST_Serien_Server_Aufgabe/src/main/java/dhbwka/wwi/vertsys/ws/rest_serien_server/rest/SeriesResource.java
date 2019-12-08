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

import dhbwka.wwi.vertsys.ws.rest_serien_server.ejb.SeriesBean;
import dhbwka.wwi.vertsys.ws.rest_serien_server.jpa.Series;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.ConstraintViolationException;
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

/**
 * REST-Webservice für Serien. Hier können Serien abgerufen, angelegt und
 * gelöscht werden.
 */
@Path("Series")
@Consumes({"application/json", "text/xml"})
@Produces({"application/json", "text/xml"})
public class SeriesResource {

    @EJB
    private SeriesBean seriesBean;

    /**
     * GET /api/Series/
     *
     * GET /api/Series/?query=…
     *
     * Auslesen einer Liste von Serien. Optional kann an die URL der Parameter
     * ?query=… angehängt werden, um die Liste auf bestimmte Serien passend zum
     * übergebenen Suchbegriff zu begrenzen.
     *
     * @param query Suchbegriff
     * @return Eine Liste mit allen gefundenen Serien
     */
    @GET
    public List<Series> findSeries(@QueryParam("query") @DefaultValue("") String query) {
        return this.seriesBean.findByQuery(query);
    }
    
    /**
     * POST /api/Series/
     * 
     * Anlagen einer neuen Serie.
     * 
     * @param series Anzulegende Serie
     * @return Gespeicherte Serie
     * @throws ConstraintViolationException 
     */
    @POST
    public Series saveNewSeries(@Valid Series series) throws ConstraintViolationException {
        return this.seriesBean.saveNew(series);
    }

    /**
     * GET /api/Series/{id}/
     *
     * Auslesen einer einzelnen Serie anhand ihrer ID.
     *
     * @param id Gesuchte Serien-Id
     * @return Gesuchte Serie oder null
     */
    @Path("{id}")
    @GET
    public Series getSeries(@PathParam("id") String id) {
        return this.seriesBean.findById(id);
    }

    /**
     * PUT /api/Series/{id}/
     *
     * Aktualisieren einer vorhandenen Serie.
     *
     * @param series Zu aktualisierende Serie
     * @return Gespeicherte Serie
     */
    @Path("{id}")
    @PUT
    public Series updateSeries(@Valid Series series) throws ConstraintViolationException {
        return this.seriesBean.update(series);
    }

    /**
     * DELETE /api/Series/{id}/
     *
     * Löschen einer vorhandenen Serie.
     *
     * @param id Zu löschende Serie
     * @return Gelöschte Serie
     */
    @Path("{id}")
    @DELETE
    public Series deleteSeries(@PathParam("id") String id) {
        Series series = this.seriesBean.findById(id);

        if (series != null) {
            this.seriesBean.delete(series);
        }

        return series;
    }

}
