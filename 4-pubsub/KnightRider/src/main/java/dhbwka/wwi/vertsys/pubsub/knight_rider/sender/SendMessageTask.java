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
import java.util.TimerTask;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Runnable, das automatisch alle paar Sekunden aufgerufen wird, um ein neues
 * KnightRider-Zitat zu versenden.
 */
public class SendMessageTask extends TimerTask {

    @Override
    public void run() {       
        // Nächste Nachricht ermitteln
        int nr = (int) Math.floor(Math.random() * SenderMain.QUOTES.size());
        String quote = SenderMain.QUOTES.get(nr);
        System.out.println("[Sende] " + quote);

        // Und ab die Post
        try {
            MqttMessage message = new MqttMessage(quote.getBytes(StandardCharsets.UTF_8));
            message.setQos(0);
            SenderMain.client.publish(Utils.MQTT_TOPIC_QUOTE, message);
        } catch (MqttException ex) {
            Utils.logException(ex);
        }
    }
}
