/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.wastebin.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Startseite: Zeigt eine Übersicht der vorhandenen Textschnippsel sowie einen
 * Link zum Anlegen neuer Schnippsel.
 */
@WebServlet(urlPatterns="/index.html")
public class IndexServlet extends HttpServlet {
    
    public static final String URL = "/index.html";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        // TODO: Vorhandene Schnippsel von der Datenbank einlesen
        // TODO: Vorhandene Schnippsel unter "allWaste" im Request Context ablegen
        // TODO: Anfrage an die /WEB-INF/index.jsp weiterleiten
        
    }
    
}
