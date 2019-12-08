/*
 * Copyright © 2019 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.codegolf.jpa;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * Hilfsklasse zum Speichern der Anzahl Abschläge an einem Loch.
 */
@Embeddable
public class Stroke implements Serializable {
    
    private int hole = 0;
    private int amount = 0;
    
    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public Stroke() {
    }
    
    public Stroke(int hole, int amount) {
        this.hole = hole;
        this.amount = amount;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getter und Setter">
    public int getHole() {
        return hole;
    }
    
    public void setHole(int hole) {
        this.hole = hole;
    }
    
    public int getAmount() {
        return amount;
    }
    
    public void setAmount(int amount) {
        this.amount = amount;
    }
    //</editor-fold>
        
}
