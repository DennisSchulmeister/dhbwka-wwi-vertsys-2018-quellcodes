/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.sessionscoped_ejb_richtig;

import java.io.IOException;
import java.io.PrintWriter;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet für unseren kleinen Versuch. Gibt einfach den Wert aus, den die
 * CounterBean hochzählt. Wenn alles richtig läuft, müssten unterschiedliche
 * Besucher ihren eigenen Zähler besitzen. Hierfür die Seite am besten in zwei
 * Browsern aufrufen und testen.
 */
@WebServlet(urlPatterns = {"/index.html"})
public class IndexServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Bean-Instanz besorgen und im Session Context ablegen
        HttpSession session = request.getSession();
        CounterBean counterBean = (CounterBean) session.getAttribute("counterBean");

        if (counterBean == null) {
            try {
                InitialContext ctx = new InitialContext();
                counterBean = (CounterBean) ctx.lookup("java:global/SessionScoped_EJB_Richtig/CounterBean");
            } catch (NamingException ex) {
                throw new ServletException(ex);
            }

            session.setAttribute("counterBean", counterBean);
        }

        // Aufruf der Bean
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.println("Deine Session ID: " + session.getId());
        out.println("Dein Zählerstand: " + counterBean.getCount());

        out.flush();
    }
}
