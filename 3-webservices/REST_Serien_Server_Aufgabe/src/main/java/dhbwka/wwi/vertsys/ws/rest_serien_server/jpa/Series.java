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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Einfache Entity-Klasse für eine Serie.
 */
@Entity
public class Series implements Serializable {

    @Id
    @Column(length = 15)
    private String id;

    @Column(length = 50)
    private String title = "";
    
    @Column(length = 50)
    private String genre = "";
    
    @Column(length = 20)
    private String country = "";

    private int fromYear = -1;
    private int toYear = -1;
    private int numberOfSeasons = -1;
    private int numberOfEpisodes = -1;

    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public Series() {
    }

    public Series(String id) {
        this.id = id;
    }
    
    public Series(String id, String title, String genre, String country, int fromYear, int toYear, int numberOfSeasons, int numberOfEpisodes) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.country = country;
        this.fromYear = fromYear;
        this.toYear = toYear;
        this.numberOfSeasons = numberOfSeasons;
        this.numberOfEpisodes = numberOfEpisodes;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setter und Getter">
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getFromYear() {
        return fromYear;
    }

    public void setFromYear(int fromYear) {
        this.fromYear = fromYear;
    }

    public int getToYear() {
        return toYear;
    }

    public void setToYear(int toYear) {
        this.toYear = toYear;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }
    //</editor-fold>

}
