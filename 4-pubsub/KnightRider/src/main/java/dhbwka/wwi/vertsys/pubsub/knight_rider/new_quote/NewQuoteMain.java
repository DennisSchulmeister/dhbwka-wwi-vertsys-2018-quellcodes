/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.pubsub.knight_rider.new_quote;

import dhbwka.wwi.vertsys.pubsub.knight_rider.Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Kleines Progrämmchen, das ein neues Zitat an den Sender schickt, damit dieses
 * es in seinen Vorrat aufnimmt.
 */
public class NewQuoteMain {

    public static void main(String[] args) {
        try {
            // Verbindung zum MQTT-Broker herstellen
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            //options.setUserName("USERNAME");
            //options.setPassword("PASSWORD".toCharArray());

            String clientId = "KnightRider-NewQuote-" + System.currentTimeMillis();
            System.out.println("Client ID: " + clientId);
            
            MqttClient client = new MqttClient(Utils.MQTT_BROKER_ADDRESS, clientId);
            client.connect(options);
            
            // Anwender nach dem neuen Zitat fragen:
            BufferedReader fromKeyboard = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.print("Neues Zitat: ");
            String quote = fromKeyboard.readLine();
            
            // Zitat an den Server schicken
            MqttMessage message = new MqttMessage(quote.getBytes(StandardCharsets.UTF_8));
            message.setQos(0);
            client.publish(Utils.MQTT_TOPIC_ADD_QUOTE, message);

            // Verbinden trennen
            client.disconnect();
        } catch (IOException | MqttException ex) {
            Utils.logException(ex);
        }
    }
}
