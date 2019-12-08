/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.sessionscoped_ejb_richtig;

import javax.ejb.Stateful;

/**
 * Hier jetzt die richtige Version (vgl. Projekt "SessionScoped_EJB_Falsch").
 * Die Annotation @SessionScoped ist bogus!
 */
@Stateful
public class CounterBean {
    
    private int count = 0;
    
    public int getCount() {
        return ++this.count;
    }
    
}
