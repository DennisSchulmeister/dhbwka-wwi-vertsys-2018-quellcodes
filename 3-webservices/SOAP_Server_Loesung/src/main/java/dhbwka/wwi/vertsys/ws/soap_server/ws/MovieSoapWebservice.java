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
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * SOAP-Webservice auf Grundlage der MovieBean.
 */
@Stateless
@WebService
public class MovieSoapWebservice {

    @EJB
    UserBean userBean;
    
    @EJB
    MovieBean movieBean;

    //
    // Ungeschützte Methode, ohne Benutzeranmeldung aufrufbar
    //
    
    @WebMethod
    @WebResult(name = "movie")
    public List<Movie> findByName(@WebParam(name = "name") String name) {
        return this.movieBean.findByName(name);
    }

    @WebMethod
    @WebResult(name = "movie")
    public List<Movie> findByNameContains(@WebParam(name = "name") String name) {
        return this.movieBean.findByNameContains(name);
    }

    @WebMethod
    @WebResult(name = "movie")
    public List<Movie> findAll() {
        return this.movieBean.findAll();
    }

    @WebMethod
    @WebResult(name = "movie")
    public Movie findById(@WebParam(name = "id") long id) {
        return this.movieBean.findById(id);
    }
    
    //
    // Geschützte Methoden, benötigen einen gültigen Benutzer
    //

    @WebMethod
    @WebResult(name = "movie")
    public Movie saveNewMovie(
            @WebParam(name = "username", header = true) String username,
            @WebParam(name = "password", header = true) String password,
            @WebParam(name = "movie") Movie movie
    ) throws UserBean.InvalidCredentialsException, UserBean.AccessRestrictedException {
        // Berechtigung prüfen
        userBean.validateUser(username, password, "soap-user");
        
        // Aktion ausführen
        return this.movieBean.saveNew(movie);
    }

    @WebMethod
    @WebResult(name = "movie")
    public Movie updateExistingMovie(
            @WebParam(name = "username", header = true) String username,
            @WebParam(name = "password", header = true) String password,
            @WebParam(name = "movie") Movie movie
    ) throws UserBean.InvalidCredentialsException, UserBean.AccessRestrictedException {
        // Berechtigung prüfen
        userBean.validateUser(username, password, "soap-user");
        
        // Aktion ausführen    ) {
        return this.movieBean.update(movie);
    }

    @WebMethod
    public void deleteMovie(
            @WebParam(name = "username", header = true) String username,
            @WebParam(name = "password", header = true) String password,
            @WebParam(name = "movie") Movie movie
    ) throws UserBean.InvalidCredentialsException, UserBean.AccessRestrictedException {
        // Berechtigung prüfen
        userBean.validateUser(username, password, "soap-user");
        
        // Aktion ausführen
        this.movieBean.delete(movie);
    }

}
