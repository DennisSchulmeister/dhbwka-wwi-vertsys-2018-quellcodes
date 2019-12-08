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
import java.nio.charset.StandardCharsets;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Callback-Klasse zum Empfang von Nachrichten. Sie ermöglicht es, dem Programm
 * zur Laufzeit weitere Textbausteine zu füttern, die die es alle paar Sekunden
 * raushaut.
 *
 */
public class NewMessageCallback implements MqttCallback {

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // Juhu, eine neue Nachricht :-)
        if (topic.equals(Utils.MQTT_TOPIC_ADD_QUOTE)) {
            String quote = new String(message.getPayload(), StandardCharsets.UTF_8);
            System.out.println("[Neues Zitat] " + quote);
            SenderMain.QUOTES.add(quote);
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken imdt) {
        // Eine von uns gesendete Nachricht wurde erfolgreich verschickt
    }

    @Override
    public void connectionLost(Throwable thrwbl) {
        // Ups, die Verbindung ist plötzlich weg …
    }
}
