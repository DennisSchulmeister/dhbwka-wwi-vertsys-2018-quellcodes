/*
 * Copyright © 2019 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.codegolf.web;

import dhbwka.wwi.vertsys.javaee.codegolf.ejb.ScorecardBean;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet für die Detailseite mit dem Inhalt einer Scorekarte.
 */
@WebServlet(urlPatterns = {"/new/", "/show/*"})
public class DetailsServlet extends HttpServlet {

    @EJB
    ScorecardBean scorecardBean;

    /**
     * Ausgewählte Scorekarte anzeigen
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // TODO: Anzuzeigende Scorekarte ermitteln
        // TODO: Anfrage an die /WEB-INF/details.jsp weiterleiten
    }

    /**
     * Neue Scorekarte oder Änderungen an vorhandener Scorekarte sichern.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // TODO: Zu speichernde Scorekarte ermitteln
        // TODO: Eingegebene Formularwerte auslesen
        // TODO: Scorekarte speichern
        // TODO: Formular erneut aufrufen, nun aber mit der Scorecard ID
    }

}
