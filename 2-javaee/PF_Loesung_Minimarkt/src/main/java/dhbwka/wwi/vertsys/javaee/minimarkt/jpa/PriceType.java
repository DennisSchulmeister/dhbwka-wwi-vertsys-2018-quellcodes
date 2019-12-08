/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.minimarkt.jpa;

/**
 * Preistypen.
 */
public enum PriceType {
    FIXED, ASKING;
    
    /**
     * Bezeichnung ermitteln
     *
     * @return Bezeichnung
     */
    public String getLabel() {
        switch (this) {
            case FIXED:
                return "Festpreis";
            case ASKING:
                return "Verhandlungsbasis";
            default:
                return this.toString();
        }
    }
}
