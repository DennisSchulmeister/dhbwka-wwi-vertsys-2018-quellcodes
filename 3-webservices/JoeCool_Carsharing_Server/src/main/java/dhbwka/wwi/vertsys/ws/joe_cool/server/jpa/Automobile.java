/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.joe_cool.server.jpa;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

/**
 * Entityklasse für ein Fahrzeug.
 */
@Entity
public class Automobile implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "automobile_ids")
    @TableGenerator(name = "automobile_ids", initialValue = 0, allocationSize = 5)
    private long id;
    
    @Column(length = 50)
    private String manufacturer = "";
    
    @Column(length = 50)
    private String model = "";
    
    private int buildYear = 0;
    
    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public Automobile() {
    }
    
    public Automobile(String manufacturer, String model, int buildYear) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.buildYear = buildYear;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setter und Getter">
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getManufacturer() {
        return manufacturer;
    }
    
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public int getBuildYear() {
        return buildYear;
    }
    
    public void setBuildYear(int buildYear) {
        this.buildYear = buildYear;
    }
    //</editor-fold>
    
}
