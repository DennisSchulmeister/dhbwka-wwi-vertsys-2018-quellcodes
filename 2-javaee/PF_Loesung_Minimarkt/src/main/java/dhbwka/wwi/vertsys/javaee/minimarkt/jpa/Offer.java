/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.minimarkt.jpa;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Eine zu erledigende Aufgabe.
 */
@Entity
public class Offer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "offer_ids")
    @TableGenerator(name = "offer_ids", initialValue = 0, allocationSize = 50)
    private long id;

    @NotNull(message = "Das Datum darf nicht leer sein.")
    private Date createDate = new Date(System.currentTimeMillis());

    @NotNull(message = "Die Uhrzeit darf nicht leer sein.")
    private Time createTime = new Time(System.currentTimeMillis());

    @ManyToOne
    @NotNull(message = "Das Angebot muss einem Benutzer geordnet werden.")
    private User owner;

    @Enumerated(EnumType.STRING)
    @NotNull
    private OfferType offerType = OfferType.SELL;

    @ManyToOne
    private Category category;

    @Column(length = 50)
    @NotNull(message = "Die Bezeichnung darf nicht leer sein.")
    @Size(min = 1, max = 50, message = "Die Bezeichnung muss zwischen ein und 50 Zeichen lang sein.")
    private String shortText;

    @Lob
    @NotNull
    private String longText;

    @Enumerated(EnumType.STRING)
    @NotNull
    private PriceType priceType = PriceType.ASKING;

    @Column(scale = 10, precision = 2)
    @NotNull
    private double price = 0.0;

    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public Offer() {
    }

    public Offer(User owner, OfferType offerType, Category category, String shortText,
            String longText, PriceType priceType, double price) {
        this.owner = owner;
        this.offerType = offerType;
        this.category = category;
        this.shortText = shortText;
        this.longText = longText;
        this.priceType = priceType;
        this.price = price;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setter und Getter">
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Time getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Time createTime) {
        this.createTime = createTime;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public OfferType getOfferType() {
        return offerType;
    }

    public void setOfferType(OfferType offerType) {
        this.offerType = offerType;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getLongText() {
        return longText;
    }

    public void setLongText(String longText) {
        this.longText = longText;
    }

    public PriceType getPriceType() {
        return priceType;
    }

    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    //</editor-fold>
    
}
