/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.businessinterface_beispiel;

import dhbwka.wwi.vertsys.javaee.businessinterface_beispiel.beer.Beer;
import dhbwka.wwi.vertsys.javaee.businessinterface_beispiel.greeting.Greeting;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Einfaches Servlet, dass all unsere verschiedenen EJBs aufruft.
 */
@WebServlet(urlPatterns={"/index.html"})
public class IndexServlet extends HttpServlet {
    
    // Grüßdienst
    @EJB(beanName="GermanGreeting")
    Greeting germanGreeting;
    
    @EJB(beanName="BavarianGreeting")
    Greeting bavarianGreeting;
    
    @EJB(beanName="DogGreeting")
    Greeting dogGreeting;
    
    // Bierempfehlungsdienst
    @EJB(beanName="BavarianBeer")
    Beer bavarianBeer;
    
    @EJB(beanName="CologneBeer")
    Beer cologneBeer;
    
    @EJB(beanName="DüsseldorfBeer")
    Beer duesseldorfBeer;
    
    @EJB(beanName="KarlsruheBeer")
    Beer karlsruheBeer;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        
        out.println("====================================");
        out.println("Das internationale Begrüßungskomitee");
        out.println("====================================");
        out.println();
        out.println("Deutsch:  " + germanGreeting.getGreeting());
        out.println("Bayrisch: " + bavarianGreeting.getGreeting());
        out.println("Hund:     " + dogGreeting.getGreeting());
        
        out.println();
        out.println();
        out.println();
        
        out.println("========================");
        out.println("Internationale Biermesse");
        out.println("========================");
        out.println();
        out.println("Bayrisches Bier:   " + bavarianBeer.getRecommendetBeer());
        out.println("Kölner Bier:       " + cologneBeer.getRecommendetBeer());
        out.println("Düsseldorfer Bier: " + duesseldorfBeer.getRecommendetBeer());
        out.println("Karlsruher Bier:   " + karlsruheBeer.getRecommendetBeer());

        out.flush();
    }
}
