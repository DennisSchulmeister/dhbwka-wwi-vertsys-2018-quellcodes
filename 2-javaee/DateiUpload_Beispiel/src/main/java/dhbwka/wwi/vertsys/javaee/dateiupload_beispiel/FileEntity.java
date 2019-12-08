/*
 * Copyright © 2019 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.dateiupload_beispiel;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.TableGenerator;

/**
 * Einfache Entity-Bean zum Ablegen von Dateiinhalten in der Datenbank.
 * 
 * Bei kleinen Anwendungen, die nur wenige Dateien speichern müssen,
 * kann man die Dateiinhalte durchaus in de Datenbank ablegen. Bei
 * größeren Anwendungen sollte man in der Datenbank jedoch nur die
 * Metadaten und die Dateien selbst in einem Verzeichnis auf dem Server
 * ablegen. Dadurch wächst dann die Datenbank nicht so sehr an und man
 * kann zum Ausliefern der Dateien an den Client einen spezialisierten
 * Webserver mit aggressivem Caching nutzen.
 */
@Entity
public class FileEntity implements Serializable {
    
    @Id
    @GeneratedValue
    @TableGenerator(name = "file_ids", initialValue = 0, allocationSize = 10)
    private Long id;
    
    private String filename = "";
    
    @Lob
    private byte[] filecontent = {};
    
    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public FileEntity() {
    }
    
    public FileEntity(String filename, byte[] filecontent) {
        this.filename = filename;
        this.filecontent = filecontent;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setter und Getter">
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFilename() {
        return filename;
    }
    
    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    public byte[] getFilecontent() {
        return filecontent;
    }
    
    public void setFilecontent(byte[] filecontent) {
        this.filecontent = filecontent;
    }
    //</editor-fold>
}
