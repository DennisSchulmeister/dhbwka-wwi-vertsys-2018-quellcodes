/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.rest_serien_server.rest;

import dhbwka.wwi.vertsys.ws.rest_serien_server.ejb.UserBean;
import javax.ejb.EJB;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Minimaler REST-Webservice, mit dem sich ein neuer Benutzer registrieren kann.
 *
 * HINWEIS: Standardmäßig ist die Authentifizierung in diesem Beispiel
 * deaktiviert. Um sie einzuschalten, muss in der web.xml das __DISABLED__ aus
 * der Konfiguration entfernt werden-
 */
@Path("Users")
@Consumes({"application/json", "text/xml"})
@Produces({"application/json", "text/xml"})
public class SignUpUser {

    @EJB
    UserBean userBean;

    /**
     * Datencontainer für eine SignUp-Anfrage.
     */
    public static class SignUpRequest {

        @Size(min = 1, max = 64, message = "Der Benutzername muss zwischne einem und 64 Zeichen lang sein")
        @NotNull(message = "Der Benutzername darf nicht leer sein")
        public String username;
        
        @Size(min = 1, max = 64, message = "Das Passwort muss zwischne einem und 64 Zeichen lang sein")
        @NotNull(message = "Das Passwort darf nicht leer sein")
        public String password;
    }

    /**
     * Neuen Benutzer anlegen.
     *
     * @param request Daten das anzulegenden Benutzers
     * @return Bestätigung oder Fehlermeldung
     * @throws UserBean.UserAlreadyExistsException
     * @throws ConstraintViolationException
     */
    @POST
    public StatusResponse signUp(@Valid SignUpRequest request)
            throws UserBean.UserAlreadyExistsException, ConstraintViolationException {
        this.userBean.signup(request.username, request.password, "rest-beispiel-user");

        StatusResponse response = new StatusResponse();
        response.status = "OK";
        response.message = "Benutzer wurde angelegt";
        return response;
    }
}
