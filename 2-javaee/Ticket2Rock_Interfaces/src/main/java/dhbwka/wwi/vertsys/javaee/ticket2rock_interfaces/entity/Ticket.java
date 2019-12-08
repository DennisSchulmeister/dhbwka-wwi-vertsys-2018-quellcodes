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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Persistence Entity für ein Ticket
 */
@Entity
public class Ticket implements Serializable {
    
    @Id
    @GeneratedValue
    private long id = 0;
    
    @ManyToOne
    Event event = null;
    private String owner = "";
    
    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public Ticket() {
    }
    
    public Ticket(Event event, String owner) {
        this.event = event;
        this.owner = owner;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setter und Getter">
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public Event getEvent() {
        return event;
    }
    
    public void setEvent(Event event) {
        this.event = event;
    }
    
    public String getOwner() {
        return owner;
    }
    
    public void setOwner(String owner) {
        this.owner = owner;
    }
    //</editor-fold>
}
