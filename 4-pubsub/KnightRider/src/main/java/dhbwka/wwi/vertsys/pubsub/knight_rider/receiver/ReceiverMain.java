/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.pubsub.knight_rider.receiver;

import dhbwka.wwi.vertsys.pubsub.knight_rider.Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Kleines Progrämmchen, das KnightRider-Zitate von einem MQTT-Server empfängt
 * und auf der Konsole ausgibt.
 */
public class ReceiverMain {

    public static void main(String[] args) {
        try {
            // Verbindung zum MQTT-Broker herstellen            
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            //options.setUserName("USERNAME");
            //options.setPassword("PASSWORD".toCharArray());

            String clientId = "KnightRider-Receiver-" + System.currentTimeMillis();
            
            System.out.println("Client ID: " + clientId);
            System.out.println("Starte Empfang. Drücke ENTER zum Beenden.");
            System.out.println();
            
            MqttClient client = new MqttClient(Utils.MQTT_BROKER_ADDRESS, clientId);
            client.connect(options);

            // Callback-Registrieren, der die Nachrichten empfängt
            client.subscribe(Utils.MQTT_TOPIC_QUOTE);

            client.setCallback(new MqttCallback() {
                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    String quote = new String(message.getPayload(), StandardCharsets.UTF_8);
                    System.out.println("[Empfange] " + quote);
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken imdt) {
                }

                @Override
                public void connectionLost(Throwable thrwbl) {
                }
            });

            // Warten, bis das Programm beendet werden soll
            new BufferedReader(new InputStreamReader(System.in)).readLine();

            // Verbinden trennen, alle Threads stoppen
            client.disconnect();
            System.exit(0);
        } catch (IOException | MqttException ex) {
            Utils.logException(ex);
        }
    }

}
