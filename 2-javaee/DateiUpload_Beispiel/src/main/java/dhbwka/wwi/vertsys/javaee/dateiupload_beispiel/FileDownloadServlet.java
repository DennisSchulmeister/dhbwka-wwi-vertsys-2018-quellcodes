/*
 * Copyright © 2019 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.dateiupload_beispiel;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Gegenstück zum FileUploadServlet, mit dem die in der Datenbank abgelegten
 * Dateien vom Server wieder abgerufen werden können.
 */
@WebServlet(urlPatterns = {"/file"})
public class FileDownloadServlet extends HttpServlet {

    @EJB
    private FileBean fileBean;

    /**
     * HTTP-GET: Dateiinhalt zurückliefern
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // ID der gesuchten Datei ermitteln
        long id = 0;

        try {
            id = Long.parseLong(request.getParameter("id"));
        } catch (NumberFormatException | NullPointerException ex) {
            response.sendError(404, "File not found (ID missing)");
            return;
        }
        
        // Datei aus der Datenbank lesen
        FileEntity file = fileBean.findById(id);
        
        if (file == null) {
            response.sendError(404, "File not found (unknown ID)");
            return;
        }
        
        // Dateiinhalt an den Client schicken
        response.setContentLength(file.getFilecontent().length);
        
        for (int b : file.getFilecontent()) {
            response.getOutputStream().write(b);
        }
        
        response.getOutputStream().flush();
    }

}
