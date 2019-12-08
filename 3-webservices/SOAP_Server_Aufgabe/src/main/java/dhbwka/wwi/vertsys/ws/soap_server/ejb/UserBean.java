/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.soap_server.ejb;

import dhbwka.wwi.vertsys.ws.soap_server.jpa.User;
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
     * Anmeldedaten eines Benutzers sowie Benutzergruppenzuordnung prüfen.
     * Einfacher Ersatz für @RolesAllowed, was mit EJBs und Webservices nur sehr
     * schwer zum Laufen zu bringen ist, wenn der Aufruf nicht aus einem Servlet
     * heraus erfolgt.
     *
     * @param username Benutzername
     * @param password Passwort
     * @param groups Zu prüfende Gruppen (min. eine muss zugeordnet sein)
     * @return Benutzer
     *
     * @throws UserBean.InvalidCredentialsException
     * @throws UserBean.AccessRestrictedException
     */
    public User validateUser(String username, String password, String... groups)
            throws InvalidCredentialsException, AccessRestrictedException {

        // Benutzer suchen und Passwort prüfen
        User user = em.find(User.class, username);
        boolean authorize = false;

        if (user == null || !user.checkPassword(password)) {
            throw new InvalidCredentialsException("Benutzername oder Passwort sind falsch.");
        }

        // Zugeordnete Benutzergruppen prüfen, mindestens eine muss vorhanden sein
        for (String group : groups) {
            if (user.getGroups().contains(group)) {
                authorize = true;
                break;
            }
        }

        if (!authorize) {
            throw new AccessRestrictedException("Sie sind zur Durchführung dieser Operation nicht berechtigt.");
        }

        // Alles okay!
        return user;
    }

    /**
     * Prüft, ob es mindestens einen Benutzer gibt, der der gesuchten
     * Benutzergruppe zugeordnet ist. Wird in der InitAdminUser-EJB genutzt, um
     * zu erkennen, ob es mindestens einen Admin-Benutzer gibt.
     *
     * @param group Gesuchte Benutzergruppe
     * @return true, wenn es mindestens einen Benutzer gibt
     */
    public boolean findAtLeastOne(String group) {
        int count = em.createQuery("SELECT count(u.username) FROM User u WHERE :group MEMBER OF u.groups")
                .setParameter("group", group)
                .getFirstResult();

        return count > 0;
    }

    /**
     * Gibt den Benutzer mit dem gesuchten Benutzernamen zurück.
     *
     * @param username Benutzername
     * @return User oder null
     */
    public User findByUsername(String username) {
        return em.find(User.class, username);
    }

    /**
     * Änderungen speichern.
     *
     * @param user Benutzer
     * @return Selbes Benutzerobjekt
     */
    public User update(User user) {
        return em.merge(user);
    }

    /**
     * Benutzer löschen.
     *
     * @param user Benutzer
     */
    public void delete(User user) {
        em.remove(user);
    }

    //<editor-fold defaultstate="collapsed" desc="Exceptions">
    /**
     * Fehler: Der Benutzername ist bereits vergeben.
     */
    public class UserAlreadyExistsException extends Exception {

        public UserAlreadyExistsException(String message) {
            super(message);
        }
    }

    /**
     * Fehler: Anmeldedaten des Benutzers stimmen nicht
     */
    public class InvalidCredentialsException extends Exception {

        public InvalidCredentialsException(String message) {
            super(message);
        }
    }

    /**
     * Fehler: Benutzer besitzt nicht die erforderliche Benutzergruppe.
     */
    public class AccessRestrictedException extends Exception {

        public AccessRestrictedException(String message) {
            super(message);
        }
    }
    //</editor-fold>

}
