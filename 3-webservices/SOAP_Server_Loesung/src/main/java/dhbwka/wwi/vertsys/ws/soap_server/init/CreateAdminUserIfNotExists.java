/*
 * Copyright © 2019 Dennis Schulmeister-Zimolong
 *
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 *
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.soap_server.init;

import dhbwka.wwi.vertsys.ws.soap_server.ejb.UserBean;
import dhbwka.wwi.vertsys.ws.soap_server.jpa.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Beim Start der Anwendung prüfen, ob es einen Admin-Benutzer gibt. Wenn nicht,
 * den Benutzer admin/admin anlegen.
 */
@Singleton
@Startup
public class CreateAdminUserIfNotExists {

    private static final String DEFAULT_USERNAME = "admin";
    private static final String DEFAULT_PASSWORD = "admin";
    private static final String[] DEFAULT_GROUPS = {"soap-admin", "soap-user"};

    @EJB
    private UserBean userBean;

    @PostConstruct
    public void execute() {
        // Prüfen, ob mindestens ein Admin-Benutzer vorhanden ist
        if (userBean.findAtLeastOne("soap-admin")) {
            return;
        }

        // Benutzer aktualisieren oder neuanlegen
        User user = userBean.findByUsername(DEFAULT_USERNAME);

        if (user != null) {
            for (String group : DEFAULT_GROUPS) {
                user.addToGroup(group);
            }

            userBean.update(user);
        } else {
            try {
                userBean.signup(DEFAULT_USERNAME, DEFAULT_PASSWORD, DEFAULT_GROUPS);
            } catch (UserBean.UserAlreadyExistsException ex) {
                Logger.getLogger(CreateAdminUserIfNotExists.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
