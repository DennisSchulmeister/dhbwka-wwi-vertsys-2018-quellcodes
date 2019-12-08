/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.generische_entity_ejb.beispiel;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Kleines Servlet zum Testen unserer Beans.
 */
@WebServlet(urlPatterns={"/index.html"})
public class FilmServlet extends HttpServlet {
    
    @EJB
    FilmBean filmBean;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Alle Filme oder nur die Filme eines Jahrs auslesen
        String jahrString = request.getParameter("jahr");
        int jahr = 0;
        List<Film> filme;
        
        try {
            jahr = Integer.parseInt(jahrString);
        } catch (NumberFormatException ex) {
            // Nichts zu tun
        }
        
        if (jahr < 1) {
            filme = this.filmBean.findAll();
        } else {
            filme = this.filmBean.findByJahr(jahr);
        }
        
        request.setAttribute("filme", filme);
        request.setAttribute("jahr", jahr);
        
        // Anfrage an die JSP weiterleiten
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Parameter auslesen
        String name = request.getParameter("name");
        String jahrString = request.getParameter("jahr");
        String genre = request.getParameter("genre");
        int jahr = 1;
        
        if (name == null || name.trim().isEmpty()) {
            name = "";
        }
        
        if (jahrString == null || jahrString.trim().isEmpty()) {
            jahrString = "";
        }
        
        if (genre == null || genre.trim().isEmpty()) {
            genre = "";
        }
        
        try {
            jahr = Integer.parseInt(jahrString);
        } catch (NumberFormatException ex) {
            // Nichts zu tun
        }
        
        // Neuen Satz speichern
        Film film = new Film(name, jahr, genre);
        this.filmBean.saveNew(film);
        
        // Redirect auf dieselbe Seite
        response.sendRedirect(request.getRequestURI());
    }
}
