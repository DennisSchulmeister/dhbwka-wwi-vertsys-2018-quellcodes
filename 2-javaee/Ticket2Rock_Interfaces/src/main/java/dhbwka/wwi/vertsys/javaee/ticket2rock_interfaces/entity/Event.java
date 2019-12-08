/*
 * Copyright © 2019 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.ticket2rock_interfaces.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Persistence Entity für eine Veranstaltung
 */
@Entity
public class Event implements Serializable {
    @Id
    @GeneratedValue
    private long id = 0;
    
    private String name = "";
    private String location = "";
    private LocalDateTime dateTime = LocalDateTime.now();
    
    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public Event() {
    }
    
    public Event (String name, String location, LocalDateTime dateTime) {
        this.name = name;
        this.location = location;
        this.dateTime = dateTime;
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
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    //</editor-fold>
}
