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
import dhbwka.wwi.vertsys.javaee.minimarkt.jpa.Category;
import dhbwka.wwi.vertsys.javaee.minimarkt.jpa.Offer;
import dhbwka.wwi.vertsys.javaee.minimarkt.jpa.OfferType;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet für die Startseite bzw. jede Seite, die eine Liste der Aufgaben
 * zeigt.
 */
@WebServlet(urlPatterns = {"/app/offers/"})
public class OfferListServlet extends HttpServlet {

    @EJB
    private CategoryBean categoryBean;
    
    @EJB
    private OfferBean offerBean;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verfügbare Kategorien und Stati für die Suchfelder ermitteln
        request.setAttribute("categories", this.categoryBean.findAllSorted());
        request.setAttribute("offerTypes", OfferType.values());

        // Suchparameter aus der URL auslesen
        String searchText = request.getParameter("search_text");
        String searchCategory = request.getParameter("search_category");
        String searchOfferType = request.getParameter("search_offer_type");

        // Anzuzeigende Aufgaben suchen
        Category category = null;
        OfferType offerType = null;

        if (searchCategory != null) {
            try {
                category = this.categoryBean.findById(Long.parseLong(searchCategory));
            } catch (NumberFormatException ex) {
                category = null;
            }
        }

        if (searchOfferType != null) {
            try {
                offerType = OfferType.valueOf(searchOfferType);
            } catch (IllegalArgumentException ex) {
                offerType = null;
            }

        }

        List<Offer> offers = this.offerBean.search(searchText, category, offerType);
        request.setAttribute("offers", offers);

        // Anfrage an die JSP weiterleiten
        request.getRequestDispatcher("/WEB-INF/app/offer_list.jsp").forward(request, response);
    }
}
