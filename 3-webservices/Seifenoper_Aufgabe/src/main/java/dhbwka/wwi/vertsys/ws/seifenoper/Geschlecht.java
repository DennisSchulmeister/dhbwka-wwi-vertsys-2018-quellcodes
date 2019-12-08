/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.seifenoper;

/**
 * Konstanten für männliches und weibliches Geschlecht.
 */
public enum Geschlecht {
    WEIBLICH, MAENNLICH, UNBEKANNT;
    
    public String getLabel() {
        switch (this) {
            case WEIBLICH:
                return "Weiblein";
            case MAENNLICH:
                return "Männlein";
            case UNBEKANNT:
                return "Weiß man nicht so genau";
            default:
                return this.toString();
        }
    }
    
}
