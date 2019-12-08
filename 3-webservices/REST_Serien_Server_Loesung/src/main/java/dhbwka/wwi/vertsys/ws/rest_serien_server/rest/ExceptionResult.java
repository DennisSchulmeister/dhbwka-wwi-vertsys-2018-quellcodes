/*
 * Copyright © 2019 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.rest_serien_server.rest;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Antwortobjekt, das bei Auftreten einer Exception an den Client gesendet
 * wird.
 */
@XmlRootElement
public class ExceptionResult {
    public String exception;
    public String message;
    public List<Violation> violations;
    
    public static class Violation {
        public String path = "";
        public String message = "";
    }
}
