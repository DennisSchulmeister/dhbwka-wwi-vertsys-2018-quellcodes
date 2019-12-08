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
 * Entity-Klasse für einen Kunden.
 */
@Entity
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "customer_ids")
    @TableGenerator(name = "customer_ids", initialValue = 0, allocationSize = 5)
    private long id;

    @Column(length = 100)
    private String firstName = "";
    
    @Column(length = 100)
    private String lastName = "";
    
    @Column(length = 100)
    private String street = "";
    
    @Column(length = 10)
    private String housenumber = "";
    
    @Column(length = 10)
    private String zip = "";
    
    @Column(length = 50)
    private String city = "";
    
    @Column(length = 50)
    private String country = "";
    
    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public Customer() {
    }
    
    public Customer(String firstName, String lastName, String street, String housenumber,
            String zip, String city, String country) {
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.housenumber = housenumber;
        this.zip = zip;
        this.city = city;
        this.country = country;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getter und Setter">
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getStreet() {
        return street;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }
    
    public String getHousenumber() {
        return housenumber;
    }
    
    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }
    
    public String getZip() {
        return zip;
    }
    
    public void setZip(String zip) {
        this.zip = zip;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    //</editor-fold>
    
}
