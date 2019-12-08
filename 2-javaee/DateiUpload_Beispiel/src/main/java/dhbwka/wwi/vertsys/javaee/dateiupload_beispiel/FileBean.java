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

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Minimale Enterprise Java Bean zum Speichern und Auslesen von
 * Dateiinhalten, die wir in der Datenbank abgelegt haben.
 */
@Stateless
public class FileBean {
    
    @PersistenceContext
    protected EntityManager em;
    
    /**
     * Auslesen einer bereits vorhandenen Datei.
     * @param id ID der Datei
     * @return FileEntity
     */
    public FileEntity findById(long id) {
        return em.find(FileEntity.class, id);
    }
    
    /**
     * Auslesen einer Liste mit allen Dateien. Die Methode ist nicht
     * sehr effizient, da sie die Dateiinhalte gleich mitliest. Aber,
     * was soll's ...
     * 
     * @return Liste mit allen hochgeladenen Dateien
     */
    public List<FileEntity> findAll() {
        return em.createQuery("SELECT f FROM FileEntity f ORDER BY f.id")
                 .getResultList();
    }
    
    /**
     * Speichern einer neuen Datei in der Datenbank.
     * @param fileEntity Zu speichernde FileEntity
     * @return Gespeicherte FileEntity (wg. der ID)
     */
    public FileEntity saveNew(FileEntity fileEntity) {
        em.persist(fileEntity);
        return em.merge(fileEntity);
    }
    
    /**
     * Löschen einer Datei aus der Datenbank.
     * @param fileEntity Zu löschende FileEntity
     */
    public void delete(FileEntity fileEntity) {
        em.remove(fileEntity);
    }
}
