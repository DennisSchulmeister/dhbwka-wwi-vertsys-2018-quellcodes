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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;

/**
 * Einfache Entity-Klasse für eine Serienstaffel.
 */
@Entity
@IdClass(SeasonId.class)
public class Season implements Serializable {

    @Id
    @ManyToOne
    private Series series = null;

    @Id
    private int seasonNumber = -1;

    private int releaseYear = -1;
    private int numberOfEpisodes = -1;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "SEASON_EPISODES"
    )
    @Column(name = "EPISODE")
    @OrderBy("nr ASC")
    private List<Episode> episodes = new ArrayList<>();

    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public Season() {
    }

    public Season(Series series, int seasonNumber, int releaseYear) {
        this.series = series;
        this.seasonNumber = seasonNumber;
        this.releaseYear = releaseYear;
    }

    public Season(Series series, int seasonNumber, int releaseYear, List<Episode> episodes) {
        this.series = series;
        this.seasonNumber = seasonNumber;
        this.releaseYear = releaseYear;
        this.episodes = episodes;
        
        if (episodes != null) {
            this.numberOfEpisodes = episodes.size();
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setter und Getter">
    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;

        if (episodes != null) {
            this.numberOfEpisodes = episodes.size();
        }
    }
    //</editor-fold>

}
