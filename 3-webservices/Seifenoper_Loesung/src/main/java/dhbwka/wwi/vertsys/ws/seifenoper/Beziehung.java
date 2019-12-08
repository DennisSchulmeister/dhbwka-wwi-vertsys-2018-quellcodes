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
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;

/**
 * Persistence Entity für eine Beziehung zwischen zwei Personen.
 */
@Entity
public class Beziehung implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "person_ids")
    @TableGenerator(name = "person_ids", initialValue = 0, allocationSize = 50)
    private long id = 0L;
    
    @ManyToOne
    private Person person1;
    
    @ManyToOne
    private Person person2;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date seitWann = new Date(System.currentTimeMillis());
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date bisWann = new Date(253402214400000L); // 31.12.9999
    
    private boolean geheim = true;
    private String wieKennengelernt = "";
    
    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public Beziehung() {
    }
    
    public Beziehung(Person person1, Person person2, boolean geheim, String wieKennengelernt) {
        this.person1 = person1;
        this.person2 = person2;
        this.geheim = geheim;
        this.wieKennengelernt = wieKennengelernt;
    }
    
    public Beziehung(Person person1, Person person2, Date seitWann, boolean geheim, String wieKennengelernt) {
        this.person1 = person1;
        this.person2 = person2;
        this.seitWann = seitWann;
        this.geheim = geheim;
        this.wieKennengelernt = wieKennengelernt;
    }
    
    public Beziehung(Person person1, Person person2, Date seitWann, Date bisWann, boolean geheim, String wieKennengelernt) {
        this.person1 = person1;
        this.person2 = person2;
        this.seitWann = seitWann;
        this.bisWann = bisWann;
        this.geheim = geheim;
        this.wieKennengelernt = wieKennengelernt;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setter und Getter">
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public Person getPerson1() {
        return person1;
    }
    
    public void setPerson1(Person person1) {
        this.person1 = person1;
    }
    
    public Person getPerson2() {
        return person2;
    }
    
    public void setPerson2(Person person2) {
        this.person2 = person2;
    }
    
    public Date getSeitWann() {
        return seitWann;
    }
    
    public void setSeitWann(Date seitWann) {
        this.seitWann = seitWann;
    }
    
    public Date getBisWann() {
        return bisWann;
    }
    
    public void setBisWann(Date bisWann) {
        this.bisWann = bisWann;
    }
    
    public boolean isGeheim() {
        return geheim;
    }
    
    public void setGeheim(boolean geheim) {
        this.geheim = geheim;
    }
    
    public String getWieKennengelernt() {
        return wieKennengelernt;
    }
    
    public void setWieKennengelernt(String wieKennengelernt) {
        this.wieKennengelernt = wieKennengelernt;
    }
    //</editor-fold>
    
}
