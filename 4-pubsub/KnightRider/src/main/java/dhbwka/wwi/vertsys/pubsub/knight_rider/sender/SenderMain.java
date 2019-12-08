/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.pubsub.knight_rider.sender;

import dhbwka.wwi.vertsys.pubsub.knight_rider.Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * Sender, der alle drei Sekunden eine Textnachricht verschickt.
 */
public class SenderMain {

    static MqttClient client;

    static final List<String> QUOTES = new ArrayList<>();

    static {
        QUOTES.add("Knight Rider -- Das Java Programm");
        QUOTES.add("Er führt eine internationale Verbrecherbande.");
        QUOTES.add("Bonnie soll sich das mal anschauen.");
        QUOTES.add("Ich brauche den Turbo Boost! Jetzt!");
        QUOTES.add("Was gibt es, Devon?");
        QUOTES.add("Ich fahre gleich los und schaue mir das mal an.");
        QUOTES.add("Michael!? Sie sollten beim Fahren aufhören Computerspiele zu spielen.");
        QUOTES.add("Er kommt. Ein Mann und sein Auto sorgen für Gerechtigkeit.");
        QUOTES.add("Fortsetzung folgt");
        QUOTES.add("Super Persuit Mode an, Kumpel.");
    }

    public static void main(String[] args) {
        try {
            // Verbindung zum MQTT-Broker herstellen
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            //options.setUserName("USERNAME");
            //options.setPassword("PASSWORD".toCharArray());

            String clientId = "KnightRider-Sender-" + System.currentTimeMillis();

            System.out.println("Client ID: " + clientId);
            System.out.println("Starte Versand. Drücke ENTER zum Beenden.");
            System.out.println();

            client = new MqttClient(Utils.MQTT_BROKER_ADDRESS, clientId);
            client.connect(options);

            // Thread starten, der alle drei Sekunden, eine Nachricht schickt
            Timer timer = new Timer(true);
            timer.scheduleAtFixedRate(new SendMessageTask(), 0, 3000);

            // Callback-Registrieren, um auch Nachrichten empfangen zu können.
            // Dadurch können dem Programm weitere Textbausteine gefüttert werden,
            // die der obige Thread versenden kann.
            client.subscribe(Utils.MQTT_TOPIC_ADD_QUOTE);
            client.setCallback(new NewMessageCallback());

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
