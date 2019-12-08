/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.businessinterface_beispiel.greeting;

import javax.ejb.Stateless;

/**
 * Deutsche Version von Hallo, Welt!
 */
@Stateless(name="GermanGreeting")
public class GreetingGerman implements Greeting {
    
    @Override
    public String getGreeting() {
        return "Hallo, Welt!";
    }
}
