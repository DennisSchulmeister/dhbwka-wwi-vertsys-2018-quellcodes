/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.sessionscoped_ejb_falsch;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

/**
 * Adam Bien gilt eigentlich als großer Experte rund um Java EE. Doch die Aussage
 * auf seiner Seite ist definitiv falsch:
 * 
 * http://www.adam-bien.com/roller/abien/entry/stateful_vs_sessionscoped
 * 
 * Laut dieser Seite müsste die Stateful Bean durch das @SessionScoped an die
 * HTTP-Session des Serlvets gebunden werden, wodurch jeder Anwender eine
 * eigene Instanz bekommen müsste. Jeder Anwender würde dann einen eigenen
 * Zählerstand sehen.
 * 
 * Dem ist aber nicht so. Alle Anwender aus allen Sessions sehen denselben
 * Zählerstand, bzw. sie sehen nur dann unterschiedliche Stände, wenn sie
 * zufälligerweise von unterschiedlichen Servletinstanzen bedient werden.
 * 
 * Der Versuch, einen Kommentar zu auf der Seite von Adam Bien mit einer
 * lauffähigen Version zu posten (Vgl. SessionScoped_EJB_Richtig) fehl, da
 * Kommentare mit mehr als 1000 Zeichen als Spam abgewiesen werden. :D
 * 
 * Tja schade. Es wäre so schön gewesen …
 */
@Stateful
@SessionScoped
public class CounterBean {
    
    private int count = 0;
    
    public int getCount() {
        return ++this.count;
    }
    
}
