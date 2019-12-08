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
import java.sql.Time;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Persistent Entity für eine einfache Scorekarte beim Golfsport. Diese Klasse
 * zeigt nicht nur, wie eine Persistent Entity angelegt wird, sondern wie diese
 * noch ein eingebettetes Array sowie transiente (nicht in der Datenbank
 * gespeicherte) Felder beinhalten kann.
 *
 * Hierfür speichert die Scorekarte die Schläge je Loch in einem internen Array
 * und bietet zwei transiente Methoden zum Berechnen der Gesamtsumme sowie der
 * Punktzahl nach Stableford.
 *
 * Vgl. https://de.wikipedia.org/wiki/Stableford
 */
@Entity
public class Scorecard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "scorecard_ids")
    @TableGenerator(name = "scorecard_ids", initialValue = 0, allocationSize = 50)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date gameDate = Date.valueOf(LocalDate.now());

    @Temporal(TemporalType.TIME)
    private Time gameTime = Time.valueOf(LocalTime.now());

    private String course = "";
    private int par = 3;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "STROKES",
            joinColumns = @JoinColumn(name = "SCORECARD_ID")
    )
    private List<Stroke> strokes = new ArrayList<>();

    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public Scorecard() {
    }

    public Scorecard(Date gameDate, Time gameTime, String course, int par) {
        this.gameDate = gameDate;
        this.gameTime = gameTime;
        this.course = course;
        this.par = par;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setter und Getter">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

    public Time getGameTime() {
        return gameTime;
    }

    public void setGameTime(Time gameTime) {
        this.gameTime = gameTime;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getPar() {
        return par;
    }

    public void setPar(int par) {
        this.par = par;
    }

    public List<Stroke> getStrokes() {
        return strokes;
    }

    public void setStrokes(List<Stroke> strokes) {
        this.strokes = strokes;
    }
    //</editor-fold>

    /**
     * @return Nach Löchern sortierte Liste der Abschläge
     */
    public List<Stroke> getStrokesSorted() {
        List<Stroke> sorted = new ArrayList<>();
        sorted.addAll(this.strokes);
        sorted.sort((var lhs, var rhs) -> Integer.valueOf(lhs.getHole()).compareTo(rhs.getHole()));
        return sorted;
    }
    /**
     * @return Summe der Schläge über alle Löcher
     */
    public int getSumStrokes() {
        // TODO: Summe der Schläge über alle Löcher ausrechnen
        return -1;
    }

    /**
     * @return Punktzahl nach Stableford
     */
    public int getStableford() {
        // TODO: Stableford-Wertung ausrechnen.
        // Vgl. https://de.wikipedia.org/wiki/Stableford
        // Es dürfen nur Löcher gezählt werden, bei denen die Anzahl Schläge größer null ist.
        return -1;
    }

}
