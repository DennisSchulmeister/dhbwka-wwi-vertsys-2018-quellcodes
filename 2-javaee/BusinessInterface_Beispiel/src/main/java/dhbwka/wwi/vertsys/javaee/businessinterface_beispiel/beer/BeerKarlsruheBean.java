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

import javax.ejb.Stateless;

/**
 * Empfehlungsdiesnt für karlsruher Bier.
 */
@Stateless(name="KarlsruheBeer")
public class BeerKarlsruheBean implements Beer {

    @Override
    public String getRecommendetBeer() {
        return "Moninger";
    }
    
}
