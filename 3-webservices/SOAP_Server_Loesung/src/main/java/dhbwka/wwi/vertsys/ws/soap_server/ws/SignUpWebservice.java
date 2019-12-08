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

import dhbwka.wwi.vertsys.ws.soap_server.ejb.UserBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * Minimaler Webservice, der per "username" und "password" im SOAP-Header
 * abgesichert ist. Wir nutzen hier keinerlei Java-Funktionen zur
 * Authentifizierung, da es keine spezifizierte Möglichkeit gibt, den Anwender
 * aus dem Anwendungscode heraus zu authentifizieren. @RolesAllowed usw. sind
 * daher "Royal PITA" !!
 */
@Stateless
@WebService
public class SignUpWebservice {

    @EJB
    UserBean userBean;

    /**
     * Neuen Benutzer registrieren. Geht natürlich, ohne sich zuvor zu
     * authentifizieren. :-)
     * 
     * @param username Benutzername
     * @param password Passwort
     * @return Statuscode OK
     * @throws UserBean.UserAlreadyExistsException 
     */
    @WebMethod
    @WebResult(name = "status")
    public String signup(
            @WebParam(name = "username") String username,
            @WebParam(name = "password") String password)
            throws UserBean.UserAlreadyExistsException {

        this.userBean.signup(username, password, "soap-user");
        return "OK";
    }
    
}
