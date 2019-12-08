/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.businessinterface_beispiel.beer;

import javax.ejb.Local;

/**
 * Business Interface für einen Bierempfehlungsdienst
 */
@Local
public interface Beer {
    String getRecommendetBeer();
}
