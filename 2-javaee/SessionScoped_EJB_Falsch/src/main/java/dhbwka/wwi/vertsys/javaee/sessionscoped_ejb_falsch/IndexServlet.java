/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.sessionscoped_ejb_falsch;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet für unseren kleinen Versuch. Gibt einfach den Wert aus, den die
 * CounterBean hochzählt. Wenn alles richtig läuft, müssten unterschiedliche
 * Besucher ihren eigenen Zähler besitzen. Hierfür die Seite am besten in zwei
 * Browsern aufrufen und testen.
 */
@WebServlet(urlPatterns = {"/index.html"})
public class IndexServlet extends HttpServlet {
    
    @EJB
    CounterBean counterBean;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.println("Deine Session ID: " + request.getSession().getId());
        out.println("Dein Zählerstand: " + counterBean.getCount());
        
        out.flush();
    }
}
