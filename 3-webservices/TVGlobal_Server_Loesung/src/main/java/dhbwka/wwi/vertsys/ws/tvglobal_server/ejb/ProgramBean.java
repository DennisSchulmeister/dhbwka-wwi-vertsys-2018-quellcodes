/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.tvglobal_server.ejb;

import dhbwka.wwi.vertsys.ws.tvglobal_server.jpa.Program;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;

/**
 * Minimale EJB zum Lesen und Schreiben von Fernsehsendungen.
 */
@Stateless
public class ProgramBean extends EntityBean<Program, Long> {
    
    public ProgramBean() {
        super(Program.class);
    }
    
    public List<Program> findByStartBetween(Date startTimeFrom, Date startTimeTo) {
        
        return em.createQuery("SELECT p FROM Program p"
                            + "    WHERE p.startTime >= :startTimeFrom"
                            + "      AND p.startTime <= :startTimeTo"
                            + "    ORDER BY p.startTime, p.name")
                 .setParameter("startTimeFrom", startTimeFrom)
                 .setParameter("startTimeTo", startTimeTo)
                 .getResultList();
    }
}
