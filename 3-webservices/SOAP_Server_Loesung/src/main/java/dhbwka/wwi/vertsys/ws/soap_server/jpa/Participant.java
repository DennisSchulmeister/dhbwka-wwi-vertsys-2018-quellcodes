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

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * Eingebettete Persistence Entity für einen Beteiligten an einem Film.
 */
@Embeddable
public class Participant implements Serializable {
    
    private String name = "";
    private String position = "";
    
    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public Participant() {
    }
    
    public Participant(String name, String position) {
        this.name = name;
        this.position = position;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setter und Getter">
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPosition() {
        return position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
    //</editor-fold>

}
