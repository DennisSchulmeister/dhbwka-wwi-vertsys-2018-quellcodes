/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.pubsub.mini_chat.swing;

import dhbwka.wwi.vertsys.pubsub.mini_chat.ChatMessage;
import dhbwka.wwi.vertsys.pubsub.mini_chat.Utils;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Hilfsklasse, die alle Angelegenheiten rund um die Verbindung mit dem
 * MQTT-Broker regelt. Diese Klasse weiß, wie man sich mit dem Broker verbindet
 * und wie die Verbindung wieder getrennt werden kann. Außerdem weiß sie, wie
 * Nachrichten gesendet und empfangen werden können.
 */
public class ChatConnection implements MqttCallback {

    ChatConnectionListener listener;
    MqttClient mqttClient;

    /**
     * Konstruktor.
     *
     * @param listener Listener, der zu verschiedenen Zeitpunkten aufgerufen
     * wird
     */
    public ChatConnection(ChatConnectionListener listener) {
        this.listener = listener;
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                this.disconnect();
            } catch (MqttException ex) {
                Utils.logException(ex);
            }
        }));
    }

    /**
     * Verbindung herstellen.
     *
     * @param address Adresse des MQTT-Brokers
     * @param username Benutzername
     * @param password Passwort
     * @param chatname Chatname
     * @throws org.eclipse.paho.client.mqttv3.MqttException
     */
    public void connect(String address, String username, String password,
            String chatname) throws MqttException {

        // TODO: Verbindung zum MQTT-Broker herstellen            
        String clientId = "MiniChat-SwingClient-" + System.currentTimeMillis();
        
        // TODO: Warteschlange abonieren
        
        // Listener aufrufen
        if (this.listener != null) {
            this.listener.onConnect(address, clientId, username, chatname);
        }
    }

    /**
     * Verbindung trennen.
     *
     * @throws org.eclipse.paho.client.mqttv3.MqttException
     */
    public void disconnect() throws MqttException {
        // TODO: Verbindung trennen

        // Listener aufrufen
        if (this.listener != null) {
            this.listener.onDisconnect();
        }
    }

    /**
     * Chatnachricht senden.
     *
     * @param chatMessage Zu sendende Nachricht
     * @throws MqttException
     */
    public void sendChatMessage(ChatMessage chatMessage) throws MqttException {
        // TODO: Nachricht senden
    }

    /**
     * Die Verbindung ist abgebrochen.
     *
     * @param thrwbl Ursache
     */
    @Override
    public void connectionLost(Throwable thrwbl) {
        if (this.listener != null) {
            this.listener.onDisconnect();
        }
    }

    /**
     * Eine neue Nachricht wurde empfangen.
     *
     * @param topic Topic
     * @param message Nachricht
     * @throws Exception
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // TODO: Empfangene Nachricht verarbeiten
    }

    /**
     * Versand der aktuellen Nachricht abgeschlossen.
     *
     * @param imdt
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken imdt) {
    }

}
