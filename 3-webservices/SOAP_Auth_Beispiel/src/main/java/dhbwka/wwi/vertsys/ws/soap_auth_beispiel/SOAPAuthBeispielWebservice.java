/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.soap_auth_beispiel;

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
@WebService(serviceName = "ws")
public class SOAPAuthBeispielWebservice {

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

    /**
     * Beispiel für eine geschützte Methode. Erfordert, dass der Client im
     * SOAP-Header die Felder "username" und "password" sendet.
     * 
     * @param username Benutzername
     * @param password Passwort
     * @return Geheime Botschaft
     * 
     * @throws UserBean.InvalidCredentialsException
     * @throws UserBean.AccessRestrictedException
     */
    @WebMethod
    @WebResult(name = "message")
    public String getSecretMessage(
            @WebParam(name = "username", header = true) String username,
            @WebParam(name = "password", header = true) String password)
            throws UserBean.InvalidCredentialsException, UserBean.AccessRestrictedException {

        // Wirft eine Exception, wenn der Benutzer nicht berechtigt ist
        this.userBean.validateUser(username, password, "soap-user");
        
        // Der geschützte Code, den nicht jeder ausführen darf
        return "Streng geheim! For your eyes only!";
    }

}
