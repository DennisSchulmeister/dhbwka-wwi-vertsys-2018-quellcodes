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

import dhbwka.wwi.vertsys.ws.rest_songs_server.ejb.UserBean;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Minimaler REST-Webservice, mit dem sich ein neuer Benutzer registrieren kann.
 *
 * HINWEIS: Standardmäßig ist die Authentifizierung in diesem Beispiel
 * deaktiviert. Um sie einzuschalten, muss in der web.xml das __DISABLED__ aus
 * der Konfiguration entfernt werden-
 */
@Path("Users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SignUpUser {

    @EJB
    UserBean userBean;

    /**
     * Datencontainer für eine SignUp-Anfrage.
     */
    public static class SignUpRequest {

        public String username;
        public String password;
    }

    /**
     * Neuen Benutzer anlegen.
     *
     * @param request Daten das anzulegenden Benutzers
     * @return Bestätigung oder Fehlermeldung
     * @throws UserBean.UserAlreadyExistsException
     */
    @POST
    public StatusResponse signUp(SignUpRequest request) throws UserBean.UserAlreadyExistsException {
        this.userBean.signup(request.username, request.password, "rest-beispiel-user");

        StatusResponse response = new StatusResponse();
        response.status = "OK";
        response.message = "Benutzer wurde angelegt";
        return response;
    }
}
