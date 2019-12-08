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
import java.util.Objects;

/**
 * Schlüsselwerte für eine Serienstaffel.
 */
public class SeasonId implements Serializable {
    private String series = "";
    private int seasonNumber = -1;
    
    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public SeasonId() {
    }
    
    public SeasonId(String series, int seasonNumber) {
        this.series = series;
        this.seasonNumber = seasonNumber;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setter und Getter">
    public String getSeries() {
        return series;
    }
    
    public void setSeries(String series) {
        this.series = series;
    }
    
    public int getSeasonNumber() {
        return seasonNumber;
    }
    
    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Von Object geerbter Kram">
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.series);
        hash = 37 * hash + this.seasonNumber;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SeasonId other = (SeasonId) obj;
        if (this.seasonNumber != other.seasonNumber) {
            return false;
        }
        if (!Objects.equals(this.series, other.series)) {
            return false;
        }
        return true;
    }
    //</editor-fold>

}
