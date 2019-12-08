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

/**
 * Aufzählung mit den erlaubten Arten von Sensornachrichten.
 */
public enum SensorMessageType {
    VALUE,      // Wert des Sensors
    STATUS,     // Statusmeldung des Sensors
    COMMAND;    // Befehl an den Sensor
}
