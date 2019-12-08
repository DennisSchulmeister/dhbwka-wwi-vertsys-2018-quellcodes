/*
 * Copyright © 2019 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.codegolf.ejb;

import dhbwka.wwi.vertsys.javaee.codegolf.jpa.Scorecard;
import javax.ejb.Stateless;

/**
 * Enterprise Java Bean zum Lesen und Schreiben von Scorekarten.
 */
@Stateless
public class ScorecardBean extends EntityBean<Scorecard, Long> {
    
    /**
     * Konstruktor
     */
    public ScorecardBean() {
        super(Scorecard.class);
    }

}
