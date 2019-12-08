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
 * Basis-Interface für einen Sensor oder Aktor. Klassen, die dieses Interface
 * implementieren, können zusammen mit dem MockRunner einen Sensor oder Aktor
 * simulieren.
 */
public interface SensorActor {

    /**
     * Name des überwachten Topis ermitteln.
     * @return Überwachtes Topic
     */
    public String getTopicName();
    
    /**
     * Init-Methoden zum Versenden einer initialien Statusmeldung.
     * @return Neue Nachricht oder null
     * @throws Exception 
     */
    SensorMessage init() throws Exception;
    
    /**
     * Ende-Methode zum Versenden einer finalen Statusmeldung.
     * @return Neue Nachricht oder null
     * @throws Exception 
     */
    SensorMessage quit() throws Exception;
    
    /**
     * Aktuellen Sensorwert als SensorMessage zurückliefern
     * @return Neue Nachricht oder null
     * @throws Exception 
     */
    SensorMessage getCurrentValue() throws Exception;

    /**
     * Empfangene Nachricht verarbeiten
     * @param sensorMessage Empfangene Nachricht
     * @throws Exception 
     */
    void handleMessage(SensorMessage sensorMessage) throws Exception;
}
