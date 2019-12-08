/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.soap_server.jpa;

import dhbwka.wwi.vertsys.ws.soap_server.jpa.Participant;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.TableGenerator;

/**
 * Einfache Persistence Entity für einen Eintrag in einer Filmdatenbank.
 */
@Entity
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "movie_ids")
    @TableGenerator(name = "movie_ids", initialValue = 0, allocationSize = 50)
    private long id = 0L;

    private String name = "";
    private int releaseYear = 0;
    private String description = "";

    @ElementCollection
    @CollectionTable(
            name = "MOVIE_PARTICIPANTS",
            joinColumns = @JoinColumn(name = "MOVIE_ID")
    )
    private List<Participant> participants = new ArrayList<>();

    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public Movie() {
    }

    public Movie(String name, int relaseYear, String description, List<Participant> participants) {
        this.name = name;
        this.releaseYear = relaseYear;
        this.description = description;
        this.participants = participants;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setter und Getter">
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }
    //</editor-fold>

}
