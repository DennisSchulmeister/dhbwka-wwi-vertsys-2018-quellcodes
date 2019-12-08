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
 * Anzeigearten.
 */
public enum OfferType {
    BUY, SELL;

    /**
     * Bezeichnung ermitteln
     *
     * @return Bezeichnung
     */
    public String getLabel() {
        switch (this) {
            case BUY:
                return "Suche";
            case SELL:
                return "Biete";
            default:
                return this.toString();
        }
    }
}
