/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.rest_songs_server.jpa;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Einfache Entity-Klasse für einen Song.
 */
@Entity
public class Song implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "song_ids")
    @TableGenerator(name = "song_ids", initialValue = 0, allocationSize = 50)
    private long id = 0L;

    @Column(length = 64)
    @Size(min = 1, max = 64, message = "Der Name muss zwischen einem und 64 Zeichen lang sein.")
    @NotNull(message = "Der Name darf nicht leer sein.")
    private String name = "";
    
    @Column(length = 64)
    @Size(min = 1, max = 64, message = "Der Künstler muss zwischen einem und 64 Zeichen lang sein.")
    @NotNull(message = "Der Künstler darf nicht leer sein.")
    private String artist = "";
    
    @Column(length = 64)
    @Size(min = 1, max = 64, message = "Der Songwriter muss zwischen einem und 64 Zeichen lang sein.")
    @NotNull(message = "Der Songwriter darf nicht leer sein.")
    private String songwriters = "";
    
    private int releaseYear = 0;

    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public Song() {
    }

    public Song(String name, String artist, String songwriters, int releaseYear) {
        this.name = name;
        this.artist = artist;
        this.songwriters = songwriters;
        this.releaseYear = releaseYear;
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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSongwriters() {
        return songwriters;
    }

    public void setSongwriters(String songwriters) {
        this.songwriters = songwriters;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
    //</editor-fold>

}
