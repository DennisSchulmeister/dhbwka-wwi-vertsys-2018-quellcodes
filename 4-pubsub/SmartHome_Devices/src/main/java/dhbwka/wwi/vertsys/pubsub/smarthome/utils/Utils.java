/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.pubsub.smarthome.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Konstanten und Hilfsmethoden.
 */
public class Utils {

    // Hilfsklasse zum Protokollieren von Fehlern
    public static final Logger LOGGER = Logger.getLogger(Utils.class.getName());

    // Adresse des MQTT-Brokers und Namen der Topics
    public static final String MQTT_BROKER_ADDRESS = "tcp://iot.eclipse.org:1883";
    public static final String MQTT_TOPIC_NAME = "MySmartHome";

    /**
     * Exception ausgeben
     *
     * @param t Exception
     */
    public static void logException(Throwable t) {
        LOGGER.log(Level.SEVERE, null, t);
    }

}
