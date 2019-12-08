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
import dhbwka.wwi.vertsys.javaee.codegolf.jpa.Scorecard;
import dhbwka.wwi.vertsys.javaee.codegolf.jpa.Stroke;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
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

        // Anzuzeigende Scorekarte ermitteln
        Scorecard scorecard = this.getScorecardFromPath(request);
        List<Stroke> strokes = scorecard.getStrokesSorted();
        
        request.setAttribute("scorecard", scorecard);
        request.setAttribute("strokes", strokes);

        // Weiterleitung an die Java Server Page
        request.getRequestDispatcher("/WEB-INF/details.jsp").forward(request, response);
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

        // Zu speichernde Scorekarte ermitteln
        Scorecard scorecard = this.getScorecardFromPath(request);

        // Eingegebene Formularwerte auslesen
        scorecard.setGameDate(this.getParameterDate(request, "gameDate"));
        scorecard.setGameTime(this.getParameterTime(request, "gameTime"));
        scorecard.setCourse(this.getParameterString(request, "course"));
        scorecard.setPar(this.getParameterInt(request, "par"));
        
        scorecard.setStrokes(new ArrayList<>());
        int amountStrokes = this.getParameterInt(request, "amountStrokes");
        
        for (int i = 0; i < amountStrokes; i++) {
            int amount = this.getParameterInt(request, "strokes[" + i + "]");
            scorecard.getStrokes().add(new Stroke(i, amount));
        }
        
        // Scorekarte speichern
        if (scorecard.getId() != null) {
            this.scorecardBean.update(scorecard);
        } else {
            this.scorecardBean.saveNew(scorecard);
        }
        
        // Formular erneut aufrufen, nun aber mit der Scorecard ID
        String url = request.getContextPath() + "/show/" + scorecard.getId();
        response.sendRedirect(url);
    }

    //<editor-fold defaultstate="collapsed" desc="Hilfsmethode zum Ermitteln der Scorekarte">
    /**
     * Untersucht die aufgerufene URL und gibt ein dazu passendes
     * Scorecard-Objekt zurück. Entwender eine neue Scorekarte oder eine bereits
     * in der Datenbank abgelegte Scorekarte.
     *
     * @param request HTTP-Anfrage
     * @return Gesuchte oder neue Scorekarte
     */
    private Scorecard getScorecardFromPath(HttpServletRequest request) {
        Scorecard scorecard = new Scorecard();
        
        if (request.getServletPath().equals("/show")) {
            try {
                String idString = request.getPathInfo().substring(1);
                long id = Long.parseLong(idString);
                Scorecard found = scorecardBean.findById(id);
                
                if (found != null) {
                    scorecard = found;
                }
            } catch (NumberFormatException ex) {
                // Ungültige ID empfangen
            }
        }
        
        return scorecard;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Hilfsmethoden zum Auslesen der Formularwerte">
    /**
     * Formularwert mit einem String auslesen
     *
     * @param request HTTP-Anfrage
     * @param name Name des Formularfelds
     * @return Ausgelesener String
     */
    private String getParameterString(HttpServletRequest request, String name) {
        String string = "";
        String value = request.getParameter(name);
        
        if (value != null && !value.isEmpty()) {
            string = value;
        }
        
        return string;
    }
    
    /**
     * Formular-Wert mit einem Datum auslesen.
     *
     * @param request HTTP-Anfrage
     * @param name Name des Formularfelds
     * @return Ausgelesenes Datum
     */
    private Date getParameterDate(HttpServletRequest request, String name) {
        Date date = Date.valueOf(LocalDate.now());
        String value = request.getParameter(name);
        
        if (value != null && !value.isEmpty()) {
            try {
                date = Date.valueOf(value);
            } catch (IllegalArgumentException ex) {
                // Ungültiges Datum empfangen
            }
        }
        
        return date;
    }
    
    /**
     * Formular-Wert mit einer Uhrzeit auslesen.
     *
     * @param request HTTP-Anfrage
     * @param name Name des Formularfelds
     * @return Ausgelesene Uhrzeit
     */
    private Time getParameterTime(HttpServletRequest request, String name) {
        Time time = Time.valueOf(LocalTime.now());
        String value = request.getParameter(name);
        
        if (value != null && !value.isEmpty()) {
            try {
                time = Time.valueOf(value);
            } catch (IllegalArgumentException ex) {
                // Ungültige Uhrzeit empfangen
            }
        }
        
        return time;
    }
    
    private int getParameterInt(HttpServletRequest request, String name) {
        int i = 0;
        String value = request.getParameter(name);
        
        if (value != null && !value.isEmpty()) {
            try {
                i = Integer.parseInt(value);
            } catch (NullPointerException ex) {
                // Ungültigen Integer empfangen
            }
        }
        
        return i;
    }
    //</editor-fold>

}
