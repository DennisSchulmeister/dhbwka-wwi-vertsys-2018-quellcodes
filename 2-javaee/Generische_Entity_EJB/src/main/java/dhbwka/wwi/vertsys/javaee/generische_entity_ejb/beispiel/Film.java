/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.generische_entity_ejb.beispiel;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

/**
 * Eine einfache Entity zum Testen unserer generischen EJB.
 */
@Entity
public class Film implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "film_ids")
    @TableGenerator(name = "film_ids", initialValue = 0, allocationSize = 50)
    private long id = 0;

    private String name = "";
    private int jahr = 0;
    private String genre = "";

    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    /**
     * Standardkonstruktor für ein leeres Objekt.
     */
    public Film() {
    }

    /**
     * Konstruktor zur Vorbebelegung aller Felder.
     *
     * @param name
     * @param jahr
     * @param genre
     */
    public Film(String name, int jahr, String genre) {
        this.name = name;
        this.jahr = jahr;
        this.genre = genre;
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

    public int getJahr() {
        return jahr;
    }

    public void setJahr(int jahr) {
        this.jahr = jahr;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    //</editor-fold>

}
