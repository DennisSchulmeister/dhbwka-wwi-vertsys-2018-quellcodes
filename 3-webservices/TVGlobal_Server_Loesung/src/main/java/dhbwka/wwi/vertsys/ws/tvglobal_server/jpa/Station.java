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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity-Klasse für einen Fernsehsender.
 */
@Entity
public class Station implements Serializable {
    
    @Id
    @NotNull
    @Size(min=1)
    private String code = "";
    
    @NotNull
    @Size(min=1)
    private String name = "";
    
    @NotNull
    private String description = "";
    
    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public Station() {
    }
    
    public Station(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setter und Getter">
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
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
    //</editor-fold>

}
