/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.tvglobal_server.jpa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity-Klasse für eine Sendung
 */
@Entity
public class Program implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "program_ids")
    @TableGenerator(name = "program_ids", initialValue = 0, allocationSize = 50)
    private long id = 0L;
    
    @ManyToOne
    @NotNull
    private Station station = null;
    
    @NotNull
    @Size(min = 1)
    private String name = "";
    
    @NotNull
    @Size(min = 1)
    private String description = "";
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime = new Date();
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime = new Date();
    
    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public Program() {
    }
    
    public Program(Station station, String name, String description,
            Date startTime, Date endTime) {
        
        this.station = station;
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setter und Getter">
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public Station getStation() {
        return station;
    }
    
    public void setStation(Station station) {
        this.station = station;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Date getStartTime() {
        return startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
        
    public Date getEndTime() {
        return endTime;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    //</editor-fold>
    
}
