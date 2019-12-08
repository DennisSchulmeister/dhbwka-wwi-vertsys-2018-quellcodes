/*
 * Copyright © 2019 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.junit_beispiel;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Kleine Entity-Klasse für einen Eintrag in einem Gästebuch.
 */
@Entity
public class GuestbookEntity implements Serializable {

    @Id
    @GeneratedValue
    @TableGenerator(name = "task_ids", initialValue = 0, allocationSize = 50)
    private Long id;

    @Column(length = 50)
    @NotNull(message = "Der Name darf nicht leer sein.")
    @Size(min = 1, max = 50, message = "Der Name muss zwischen ein und 50 Zeichen lang sein.")
    private String name = "";
    
    @Column(length = 100)
    @NotNull(message = "Die Nachricht darf nicht leer sein.")
    @Size(min = 1, max = 100, message = "Die Nachricht muss zwischen ein und 100 Zeichen lang sein.")
    private String message = "";
    
    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public GuestbookEntity() {
    }
    
    public GuestbookEntity(String name, String message) {
        this.name = name;
        this.message = message;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setter und Getter">
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    //</editor-fold>
}
