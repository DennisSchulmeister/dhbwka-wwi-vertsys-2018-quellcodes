/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.minimarkt.web;

import dhbwka.wwi.vertsys.javaee.minimarkt.ejb.CategoryBean;
import dhbwka.wwi.vertsys.javaee.minimarkt.ejb.OfferBean;
import dhbwka.wwi.vertsys.javaee.minimarkt.ejb.UserBean;
import dhbwka.wwi.vertsys.javaee.minimarkt.ejb.ValidationBean;
import dhbwka.wwi.vertsys.javaee.minimarkt.jpa.Offer;
import dhbwka.wwi.vertsys.javaee.minimarkt.jpa.OfferType;
import dhbwka.wwi.vertsys.javaee.minimarkt.jpa.PriceType;
import dhbwka.wwi.vertsys.javaee.minimarkt.jpa.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Seite zum Anlegen oder Bearbeiten einer Aufgabe.
 */
@WebServlet(urlPatterns = "/app/offer/*")
public class OfferEditServlet extends HttpServlet {

    @EJB
    OfferBean offerBean;

    @EJB
    CategoryBean categoryBean;

    @EJB
    UserBean userBean;

    @EJB
    ValidationBean validationBean;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verfügbare Kategorien und Stati für die Suchfelder ermitteln
        request.setAttribute("categories", this.categoryBean.findAllSorted());
        request.setAttribute("offerTypes", OfferType.values());
        request.setAttribute("priceTypes", PriceType.values());

        // Zu bearbeitendes Angebot einlesen
        HttpSession session = request.getSession();
        User user = this.userBean.getCurrentUser();
        Offer offer = this.getRequestedOffer(request);

        if (offer.getId() == 0) {
            request.setAttribute("edit", false);        // Kein bereits vorhandener Satz
            request.setAttribute("readonly", false);    // Alle Felder sind eingabebereit
        } else {
            request.setAttribute("edit", true);         // Bereits vorhandener Satz
            request.setAttribute("readonly", !offer.getOwner().getUsername().equals(user.getUsername()));  // Nur eigene Angebote eingabebereit
        }

        if (session.getAttribute("offer_form") == null) {
            // Keine Formulardaten mit fehlerhaften Daten in der Session,
            // daher Formulardaten aus dem Datenbankobjekt übernehmen
            request.setAttribute("offer_form", this.createTaskForm(offer));
        }

        // Anfrage an die JSP weiterleiten
        request.getRequestDispatcher("/WEB-INF/app/offer_edit.jsp").forward(request, response);

        session.removeAttribute("offer_form");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Nur weiter, wenn es ein eigenes Angebot ist
        request.setCharacterEncoding("utf-8");

        User user = this.userBean.getCurrentUser();
        Offer offer = this.getRequestedOffer(request);

        if (!offer.getOwner().getUsername().equals(user.getUsername())) {
            return;
        }

        // Angeforderte Aktion ausführen
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "save":
                this.saveOffer(request, response);
                break;
            case "delete":
                this.deleteOffer(request, response);
                break;
        }
    }

    /**
     * Aufgerufen in doPost(): Neue oder vorhandene Aufgabe speichern
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void saveOffer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Formulareingaben prüfen
        List<String> errors = new ArrayList<>();

        String offerOfferType = request.getParameter("offer_offer_type");
        String offerCategory = request.getParameter("offer_category");
        String offerShortText = request.getParameter("offer_short_text");
        String offerLongText = request.getParameter("offer_long_text");
        String offerPriceType = request.getParameter("offer_price_type");
        String offerPrice = request.getParameter("offer_price");

        Offer offer = this.getRequestedOffer(request);

        offer.setShortText(offerShortText);
        offer.setLongText(offerLongText);

        if (offerCategory != null && !offerCategory.trim().isEmpty()) {
            try {
                offer.setCategory(this.categoryBean.findById(Long.parseLong(offerCategory)));
            } catch (NumberFormatException ex) {
                // Ungültige oder keine ID mitgegeben
            }
        }

        if (offerOfferType != null) {
            try {
                offer.setOfferType(OfferType.valueOf(offerOfferType));
            } catch (IllegalArgumentException ex) {
                errors.add("Der ausgewählte Angebotstyp ist nicht vorhanden.");
            }
        }

        if (offerPriceType != null) {
            try {
                offer.setPriceType(PriceType.valueOf(offerPriceType));
            } catch (IllegalArgumentException ex) {
                errors.add("Der ausgewählte Preistyp ist nicht vorhanden.");
            }
        }

        if (offerPrice != null) {
            offerPrice = offerPrice.replaceAll(",", ".");

            try {
                offer.setPrice(Double.parseDouble(offerPrice));
            } catch (NumberFormatException ex) {
                errors.add("Der Preis muss als Kommazahl eingegeben werden");
            }
        }

        this.validationBean.validate(offer, errors);

        // Datensatz speichern
        if (errors.isEmpty()) {
            this.offerBean.update(offer);
        }

        // Weiter zur nächsten Seite
        if (errors.isEmpty()) {
            // Keine Fehler: Startseite aufrufen
            response.sendRedirect(WebUtils.appUrl(request, "/app/offers/"));
        } else {
            // Fehler: Formuler erneut anzeigen
            FormValues formValues = new FormValues();
            formValues.setValues(request.getParameterMap());
            formValues.setErrors(errors);

            HttpSession session = request.getSession();
            session.setAttribute("offer_form", formValues);

            response.sendRedirect(request.getRequestURI());
        }
    }

    /**
     * Aufgerufen in doPost: Vorhandene Aufgabe löschen
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void deleteOffer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Datensatz löschen
        Offer task = this.getRequestedOffer(request);
        this.offerBean.delete(task);

        // Zurück zur Übersicht
        response.sendRedirect(WebUtils.appUrl(request, "/app/offers/"));
    }

    /**
     * Zu bearbeitende Aufgabe aus der URL ermitteln und zurückgeben. Gibt
     * entweder einen vorhandenen Datensatz oder ein neues, leeres Objekt
     * zurück.
     *
     * @param request HTTP-Anfrage
     * @return Zu bearbeitende Aufgabe
     */
    private Offer getRequestedOffer(HttpServletRequest request) {
        // Zunächst davon ausgehen, dass ein neuer Satz angelegt werden soll
        Offer offer = new Offer();
        offer.setOwner(this.userBean.getCurrentUser());

        // ID aus der URL herausschneiden
        String offerId = request.getPathInfo();

        if (offerId == null) {
            offerId = "";
        }

        offerId = offerId.substring(1);

        if (offerId.endsWith("/")) {
            offerId = offerId.substring(0, offerId.length() - 1);
        }

        // Versuchen, den Datensatz mit der übergebenen ID zu finden
        try {
            offer = this.offerBean.findById(Long.parseLong(offerId));
        } catch (NumberFormatException ex) {
            // Ungültige oder keine ID in der URL enthalten
        }

        return offer;
    }

    /**
     * Neues FormValues-Objekt erzeugen und mit den Daten eines aus der
     * Datenbank eingelesenen Datensatzes füllen. Dadurch müssen in der JSP
     * keine hässlichen Fallunterscheidungen gemacht werden, ob die Werte im
     * Formular aus der Entity oder aus einer vorherigen Formulareingabe
     * stammen.
     *
     * @param offer Die zu bearbeitende Aufgabe
     * @return Neues, gefülltes FormValues-Objekt
     */
    private FormValues createTaskForm(Offer offer) {
        Map<String, String[]> values = new HashMap<>();

        if (offer.getOwner() != null) {
            values.put("owner_realname", new String[]{
                offer.getOwner().getRealname()
            });
            
            values.put("owner_street", new String[]{
                offer.getOwner().getStreet()
            });
            
            values.put("owner_postcode", new String[]{
                offer.getOwner().getPostcode()
            });
            
            values.put("owner_city", new String[]{
                offer.getOwner().getCity()
            });
            
            values.put("owner_phone", new String[]{
                offer.getOwner().getPhone()
            });
            
            values.put("owner_email", new String[]{
                offer.getOwner().getEmail()
            });
        }

        values.put("offer_create_date", new String[]{
            WebUtils.formatDate(offer.getCreateDate())
        });

        values.put("offer_create_time", new String[]{
            WebUtils.formatTime(offer.getCreateTime())
        });

        if (offer.getCategory() != null) {
            values.put("offer_category", new String[]{
                offer.getCategory().toString()
            });
        }

        values.put("offer_offer_type", new String[]{
            offer.getOfferType().toString()
        });

        values.put("offer_short_text", new String[]{
            offer.getShortText()
        });

        values.put("offer_long_text", new String[]{
            offer.getLongText()
        });

        values.put("offer_price_type", new String[]{
            offer.getPriceType().toString()
        });

        values.put("offer_price", new String[]{
            Double.toString(offer.getPrice()).replace(".", ",")
        });

        FormValues formValues = new FormValues();
        formValues.setValues(values);
        return formValues;
    }

}
