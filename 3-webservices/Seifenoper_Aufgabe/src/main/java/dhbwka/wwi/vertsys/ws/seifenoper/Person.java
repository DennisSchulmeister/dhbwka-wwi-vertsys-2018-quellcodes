/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.seifenoper;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

/**
 * Persistence Entity für eine Person in unserer kleinen Seifenoper.
 */
@Entity
public class Person implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "person_ids")
    @TableGenerator(name = "person_ids", initialValue = 0, allocationSize = 50)
    private long id = 0L;
    
    private String name = "";
    private int geburtsjahr = 0;
    private Geschlecht geschlecht = Geschlecht.UNBEKANNT;
    private String eigenheiten = "";
    
    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public Person() {
    }
    
    public Person(String name, int geburtsjahr, Geschlecht geschlecht, String eigenheiten) {
        this.name = name;
        this.geburtsjahr = geburtsjahr;
        this.geschlecht = geschlecht;
        this.eigenheiten = eigenheiten;
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
    
    public int getGeburtsjahr() {
        return geburtsjahr;
    }
    
    public void setGeburtsjahr(int geburtsjahr) {
        this.geburtsjahr = geburtsjahr;
    }
    
    public Geschlecht getGeschlecht() {
        return geschlecht;
    }
    
    public void setGeschlecht(Geschlecht geschlecht) {
        this.geschlecht = geschlecht;
    }
    
    public String getEigenheiten() {
        return eigenheiten;
    }
    
    public void setEigenheiten(String eigenheiten) {
        this.eigenheiten = eigenheiten;
    }
    //</editor-fold>
    
}
