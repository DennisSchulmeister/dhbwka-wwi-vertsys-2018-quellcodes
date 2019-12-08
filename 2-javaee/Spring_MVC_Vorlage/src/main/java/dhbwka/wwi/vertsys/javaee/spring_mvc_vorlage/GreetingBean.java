/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.spring_mvc_vorlage;

import javax.ejb.Stateless;

/**
 * Einfache EJB für das Spring-MVC-Vorlageprojekt.
 */
@Stateless
public class GreetingBean {
    
    public String getGreeting() {
        return "Servus!";
    }
    
}
