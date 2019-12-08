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
import java.io.InputStream;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Kleines Servlet einer minimalen MVC-Webanwendung, die es ermöglicht, über ein
 * Formular Dateien hochzuladen und in der Datenbank abzuspeichern.
 *
 * Nicht die schönste Lösung, da Datenbanken eigentlich nicht dafür gemacht
 * sind, dafür aber relativ einfach. Die Alternative wäre, die Bilder im
 * Dateisystem abzulegen und in der Datenbank nur die Metadaten zu speichern.
 * Dann müsste man aber durch entsprechende Serverkonfiguration oder durch
 * Verwendung eines separaten HTTP-Servers sicherstellen, dass die Bilder auch
 * wieder über HTTP abgerufen werden können. Hier in diesem Beispiel verwenden
 * wir dafür stattdessen das FileDownloadServlet.
 */
@WebServlet(urlPatterns = {"/index.html"})
@MultipartConfig
public class FileUploadServlet extends HttpServlet {

    @EJB
    private FileBean fileBean;

    /**
     * HTTP-GET: Formular anzeigen
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // Auslesen einer Liste mit allen vorhandenen Dateien
        List<FileEntity> allFiles = fileBean.findAll();
        request.setAttribute("allFiles", allFiles);

        // Anfrage an die JSP weiterreichen
        request.getRequestDispatcher("/WEB-INF/fileupload.jsp").forward(request, response);
    }

    /**
     * HTTP-POST: Hochgeladene Datei speichern
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // Hochgeladene Datei aus dem Request auslesen
        Part myFile = request.getPart("myFile");

        // … Dateiname
        String filename = myFile.getSubmittedFileName();

        // … Dateiinhalt
        InputStream myFileStream = myFile.getInputStream();
        byte[] filecontent = new byte[(int) myFile.getSize()];
        int i = 0, b;

        while ((b = myFileStream.read()) != -1) {
            filecontent[i++] = (byte) b;
        }

        // Datei speichern
        FileEntity fileEntity = new FileEntity(filename, filecontent);
        fileBean.saveNew(fileEntity);

        // Redirect zurück zum Formular
        response.sendRedirect(request.getRequestURI());
    }

}
