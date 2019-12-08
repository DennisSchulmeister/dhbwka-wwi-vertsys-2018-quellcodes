/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.rest_serien_server.ejb;

import dhbwka.wwi.vertsys.ws.rest_serien_server.jpa.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Spezielle EJB zum Anlegen eines Benutzers und Aktualisierung des Passworts.
 * Dies ist im Prinzip dieselbe EJB, wie im jTodo-Beispiel.
 */
@Stateless
public class UserBean {

    @PersistenceContext
    EntityManager em;

    /**
     * Registrieren eines neuen Benutzers
     * 
     * @param username Benutzername
     * @param password Passwort
     * @param groups Benutzergruppen
     * 
     * @throws UserBean.UserAlreadyExistsException
     */
    public void signup(String username, String password, String... groups)
            throws UserAlreadyExistsException {
        
        if (em.find(User.class, username) != null) {
            throw new UserAlreadyExistsException("Der Benutzername $B ist bereits vergeben.".replace("$B", username));
        }

        User user = new User(username, password);
        
        for (String group : groups) {
            user.addToGroup(group);
        }
        
        em.persist(user);
    }

    /**
     * Fehler: Der Benutzername ist bereits vergeben.
     */
    public class UserAlreadyExistsException extends Exception {
        
        public UserAlreadyExistsException(String message) {
            super(message);
        }
    }

}
