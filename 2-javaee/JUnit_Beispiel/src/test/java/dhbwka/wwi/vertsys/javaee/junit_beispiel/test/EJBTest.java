/*
 * Copyright © 2019 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.junit_beispiel.test;

import java.io.IOException;
import java.util.Properties;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 * Basisklasse für JUnit-Tests, die Enterprise Java Beans oder sonstige
 * JakarteEE-Funktionen testen. Diese Klasse stellt sicher, dass vor Ausführung
 * der Tests ein lokaler EJB-Container gestartet wird, in dem die Tests
 * ausgeführt werden können.
 */
public class EJBTest {

    protected static EJBContainer ejbContainer;

    @BeforeAll
    public static void startEJBContainer() throws IOException {
        // Konfiguration einlesen
        Properties properties = new Properties();
        properties.load(EJBTest.class.getClassLoader().getResourceAsStream("tomee.properties"));

        // EJB-Container starten
        ejbContainer = EJBContainer.createEJBContainer(properties);
    }

    @BeforeEach
    public void injectObjects() throws NamingException {
        // TomEE/OpenEJB-spezifischer Hack: Dependency Injection ausführen
        // Funktioniert nur, wenn die Testklasse selbst eine EJB ist, was leider nicht in der Doku steht :-(
        ejbContainer.getContext().bind("inject", this);
    }

    @AfterEach
    public void uninjectObjects() throws NamingException {
        // TomEE/OpenEJB-spezifischer Hack: Dependency Injection rückgängig machen
        // Funktioniert nur, wenn die Testklasse selbst eine EJB ist, was leider nicht in der Doku steht :-(
        ejbContainer.getContext().unbind("inject");
    }

    @AfterAll
    public static void shutdownEJBContainer() {
        // EJB-Container stoppen
        ejbContainer.close();
    }
}
