/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.joe_cool.server.ejb;

import dhbwka.wwi.vertsys.ws.joe_cool.server.jpa.Automobile;
import dhbwka.wwi.vertsys.ws.joe_cool.server.jpa.Customer;
import dhbwka.wwi.vertsys.ws.joe_cool.server.jpa.LoanContract;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;

/**
 * EJB zum Lesen und Schreiben von Leihverträgen.
 */
@Stateless
public class LoanContractBean extends EntityBean<LoanContract, Long> {
    
    public LoanContractBean() {
        super(LoanContract.class);
    }
    
    /**
     * Alle Verträge eines Kunden selektieren.
     * @param customer Kunde
     * @return Gefundene Verträge
     */
    public List<LoanContract> findByCustomer(Customer customer) {
        return em.createQuery("SELECT l FROM LoanContract l"
                            + "    WHERE l.customer = :customer"
                            + "    ORDER BY l.beginDate, l.endDate")
                 .setParameter("customer", customer)
                 .getResultList();
    }
    
    /**
     * Alle Verträge zu einem Fahrzeug selektieren, die in Konflikt mit den
     * übergebenen Datümern stehen.
     * @param automobile Gewünschtes Fahrzeug
     * @param beginDate Gewünschtes Ausleihdatum
     * @param endDate Gewünschtes Rückgabedatum
     * @return Bereits bestehende Leihverträge
     */
    public List<LoanContract> findConflictingContracts(Automobile automobile,
            Date beginDate, Date endDate) {
        
        return em.createQuery("SELECT l FROM LoanContract l"
                            + "    WHERE l.automobile.id = :automobile AND ("
                            + "              l.beginDate <= :beginDate AND"
                            + "              l.endDate   >= :beginDate"
                            + "          ) OR ("
                            + "              l.beginDate <= :endDate AND"
                            + "              l.endDate   >= :endDate"
                            + "          ) OR ("
                            + "              l.beginDate <= :beginDate AND"
                            + "              l.endDate   >= :endDate"
                            + "          )")
                 .setParameter("automobile", automobile.getId())
                 .setParameter("beginDate", beginDate)
                 .setParameter("endDate", endDate)
                 .getResultList();
    }
    
    /**
     * Speichern eines neuen Leihvertrags, aber nur, wenn das Fahrzeug im
     * gewünschten Zeitraum auch verfügbar ist.
     * 
     * @param loanContract Zu speichernder Leihvertrag
     * @return Gespeicherten Leihvertrag
     * @throws CarUnavailableException Das gewünschte Fahrzeug ist nicht verfügbar
     */
    public LoanContract saveIfNoConflict(LoanContract loanContract)
            throws CarUnavailableException{
        
        List<LoanContract> conflicts = this.findConflictingContracts(
                loanContract.getAutomobile(), loanContract.getBeginDate(),
                loanContract.getEndDate());
        
        if (!conflicts.isEmpty()) {
            throw new CarUnavailableException("Das gewünschte Fahrzeug ist in diesem Zeitraum leider nicht verfügbar.");
        }
        
        return this.saveNew(loanContract);
    }
    
    /**
     * Fehler: Das gewünschte Fahrzeug ist nicht verfügbar
     */
    public class CarUnavailableException extends Exception {
        public CarUnavailableException(String message) {
            super(message);
        }
    }
}
