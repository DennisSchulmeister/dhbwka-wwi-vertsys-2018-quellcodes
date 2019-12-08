/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.minimarkt.ejb;

import dhbwka.wwi.vertsys.javaee.minimarkt.jpa.Category;
import dhbwka.wwi.vertsys.javaee.minimarkt.jpa.Offer;
import dhbwka.wwi.vertsys.javaee.minimarkt.jpa.OfferType;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Einfache EJB mit den üblichen CRUD-Methoden für Anzeigen
 */
@Stateless
@RolesAllowed("minimarkt-app-user")
public class OfferBean extends EntityBean<Offer, Long> { 
   
    public OfferBean() {
        super(Offer.class);
    }
    
    /**
     * Alle Anzeigen eines Benutzers, nach Fälligkeit sortiert zurückliefern.
     * @param username Benutzername
     * @return Alle Aufgaben des Benutzers
     */
    public List<Offer> findByUsername(String username) {
        return em.createQuery("SELECT o FROM Offer o WHERE o.owner.username = :username ORDER BY o.createDate DESC, o.createTime DESC")
                 .setParameter("username", username)
                 .getResultList();
    }
    
    /**
     * Suche nach Aufgaben anhand ihrer Bezeichnung, Kategorie und Status.
     * 
     * Anders als in der Vorlesung behandelt, wird die SELECT-Anfrage hier
     * mit der CriteriaBuilder-API vollkommen dynamisch erzeugt.
     * 
     * @param search In der Kurzbeschreibung enthaltener Text (optional)
     * @param category Kategorie (optional)
     * @param offerType Typ (optional)
     * @return Liste mit den gefundenen Aufgaben
     */
    public List<Offer> search(String search, Category category, OfferType offerType) {
        // Hilfsobjekt zum Bauen des Query
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        
        // SELECT o FROM Offer o
        CriteriaQuery<Offer> query = cb.createQuery(Offer.class);
        Root<Offer> from = query.from(Offer.class);
        query.select(from);

        // ORDER BY dueDate, dueTime
        query.orderBy(cb.desc(from.get("createDate")), cb.desc(from.get("createTime")));
        
        // WHERE t.shortText LIKE :search
        if (search != null && !search.trim().isEmpty()) {
            query.where(cb.like(from.get("shortText"), "%" + search + "%"));
        }
        
        // WHERE t.category = :category
        if (category != null) {
            query.where(cb.equal(from.get("category"), category));
        }
        
        // WHERE t.status = :status
        if (offerType != null) {
            query.where(cb.equal(from.get("offerType"), offerType));
        }
        
        return em.createQuery(query).getResultList();
    }
}
