/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.minimarkt.web;

import dhbwka.wwi.vertsys.javaee.minimarkt.ejb.UserBean;
import dhbwka.wwi.vertsys.javaee.minimarkt.ejb.ValidationBean;
import dhbwka.wwi.vertsys.javaee.minimarkt.jpa.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Seite zum Anlegen oder Bearbeiten einer Aufgabe.
 */
@WebServlet(urlPatterns = "/app/user/")
public class UserEditServlet extends HttpServlet {

    @EJB
    UserBean userBean;

    @EJB
    ValidationBean validationBean;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Zu bearbeitenden Benutzer einlesen
        HttpSession session = request.getSession();

        User user = this.getRequestedUser(request);

        if (session.getAttribute("user_form") == null) {
            // Keine Formulardaten mit fehlerhaften Daten in der Session,
            // daher Formulardaten aus dem Datenbankobjekt übernehmen
            request.setAttribute("user_form", this.createUserForm(user));
        }

        // Anfrage an die JSP weiterleiten
        request.getRequestDispatcher("/WEB-INF/app/user_edit.jsp").forward(request, response);

        session.removeAttribute("user_form");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Angeforderte Aktion ausführen
        request.setCharacterEncoding("utf-8");

        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "save":
                this.saveUser(request, response);
                break;
            case "delete":
                this.deleteUser(request, response);
                break;
        }
    }

    /**
     * Aufgerufen in doPost(): Vorhandenen Benutzer speichern
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void saveUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Formulareingaben prüfen
        List<String> errors = new ArrayList<>();

        String oldPassword = request.getParameter("user_old_password");
        String newPassword1 = request.getParameter("user_new_password1");
        String newPassword2 = request.getParameter("user_new_password2");

        String realname = request.getParameter("user_realname");
        String street = request.getParameter("user_street");
        String postcode = request.getParameter("user_postcode");
        String city = request.getParameter("user_city");
        String phone = request.getParameter("user_phone");
        String email = request.getParameter("user_email");

        User user = this.getRequestedUser(request);

        user.setRealname(realname);
        user.setStreet(street);
        user.setPostcode(postcode);
        user.setCity(city);
        user.setPhone(phone);
        user.setEmail(email);

        if (newPassword1 != null && newPassword2 != null && !newPassword1.equals(newPassword2)) {
            errors.add("Die beiden Passwörter stimmen nicht überein.");
        }

        this.validationBean.validate(user, errors);

        // Datensatz speichern
        if (newPassword1 != null && !newPassword1.isEmpty() && errors.isEmpty()) {
            try {
                this.userBean.changePassword(user, oldPassword, newPassword1);
            } catch (UserBean.InvalidCredentialsException ex) {
                errors.add(ex.getMessage());
            }
        }

        if (errors.isEmpty()) {
            this.userBean.update(user);
        }

        // Weiter zur nächsten Seite
        if (!errors.isEmpty()) {
            FormValues formValues = new FormValues();
            formValues.setValues(request.getParameterMap());
            formValues.setErrors(errors);

            HttpSession session = request.getSession();
            session.setAttribute("user_form", formValues);
        }

        response.sendRedirect(request.getRequestURI());
    }

    /**
     * Aufgerufen in doPost: Benutzer löschen
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Datensatz löschen
        User user = this.getRequestedUser(request);
        this.userBean.delete(user);

        // Zurück zur Übersicht
        request.logout();
        response.sendRedirect(WebUtils.appUrl(request, "/app/offers/"));
    }

    /**
     * Zu bearbeitende Aufgabe aus der URL ermitteln und zurückgeben. Gibt
     * entweder einen vorhandenen Datensatz oder ein neues, leeres Objekt
     * zurück.
     *
     * @param request HTTP-Anfrage
     * @return Zu bearbeitende Aufgabe
     */
    private User getRequestedUser(HttpServletRequest request) {
        return this.userBean.getCurrentUser();
    }

    /**
     * Neues FormValues-Objekt erzeugen und mit den Daten eines aus der
     * Datenbank eingelesenen Datensatzes füllen. Dadurch müssen in der JSP
     * keine hässlichen Fallunterscheidungen gemacht werden, ob die Werte im
     * Formular aus der Entity oder aus einer vorherigen Formulareingabe
     * stammen.
     *
     * @param user Der zu bearbeitende Benutzer
     * @return Neues, gefülltes FormValues-Objekt
     */
    private FormValues createUserForm(User user) {
        Map<String, String[]> values = new HashMap<>();

        values.put("user_username", new String[]{
            user.getUsername()
        });

        values.put("user_old_password", new String[]{
            ""
        });

        values.put("user_new_password1", new String[]{
            ""
        });

        values.put("user_new_password2", new String[]{
            ""
        });

        values.put("user_realname", new String[]{
            user.getRealname()
        });

        values.put("user_street", new String[]{
            user.getStreet()
        });

        values.put("user_postcode", new String[]{
            user.getPostcode()
        });

        values.put("user_city", new String[]{
            user.getCity()
        });

        values.put("user_phone", new String[]{
            user.getPhone()
        });

        values.put("user_email", new String[]{
            user.getEmail()
        });

        FormValues formValues = new FormValues();
        formValues.setValues(values);
        return formValues;
    }

}
