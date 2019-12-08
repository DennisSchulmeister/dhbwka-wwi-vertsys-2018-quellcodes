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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.nio.charset.StandardCharsets;

/**
 * Nachricht von oder an einen Sensor.
 */
public class SensorMessage {

    public SensorMessageType type = SensorMessageType.VALUE;
    public String text = "";
    public double number = 0.0;
    public long time = System.currentTimeMillis();

    /**
     * Erzeugt ein Byte-Array mit einem JSON-String für diese Nachricht.
     *
     * @return JSON-String als UTF-8 kodiertes Byte-Array
     */
    public byte[] toJson() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this).getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Wandelt einen empfangenen JSON-String wieder zurück ein Objekt. Der
     * String muss hierzu als UTF-8 kodiertes Bytearray vorliegen.
     *
     * @param json Empfangene JSON-Daten, UTF-8 kodiert
     * @return ChatMessage-Objekts
     */
    public static SensorMessage fromJson(byte[] json) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(new String(json, StandardCharsets.UTF_8), SensorMessage.class);
    }
}
