/*
 * Copyright © 2019 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.rest_serien_server.jpa;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * Einfache Datenklasse für eine Episode. Objekte dieser Klasse werden nicht
 * als eigene Entity betrachtet, sondern als Bestandteil der Season-Entity.
 */
@Embeddable
public class Episode implements Serializable {
    private int nr = -1;
    private String title = "";
    
    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public Episode() {
    }
    
    public Episode(int nr, String title) {
        this.nr = nr;
        this.title = title;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setter und Getter">
    public int getNr() {
        return nr;
    }
    
    public void setNr(int nr) {
        this.nr = nr;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    //</editor-fold>
    
}
