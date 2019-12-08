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
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entityklasse für einen Leihvertrag.
 */
@Entity
public class LoanContract implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "contract_ids")
    @TableGenerator(name = "contract_ids", initialValue = 0, allocationSize = 5)
    private long id;
    
    @ManyToOne
    private Customer customer;
    
    @ManyToOne
    private Automobile automobile;
    
    @Temporal(TemporalType.DATE)
    private Date beginDate = new Date();
    
    @Temporal(TemporalType.DATE)
    private Date endDate = new Date();
    
    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public LoanContract() {
    }
    
    public LoanContract(Customer customer, Automobile automobile, Date beginDate,
            Date endDate) {
        
        this.customer = customer;
        this.automobile = automobile;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setter und Getter">
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public Automobile getAutomobile() {
        return automobile;
    }
    
    public void setAutomobile(Automobile automobile) {
        this.automobile = automobile;
    }
    
    public Date getBeginDate() {
        return beginDate;
    }
    
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }
    
    public Date getEndDate() {
        return endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    //</editor-fold>

}
