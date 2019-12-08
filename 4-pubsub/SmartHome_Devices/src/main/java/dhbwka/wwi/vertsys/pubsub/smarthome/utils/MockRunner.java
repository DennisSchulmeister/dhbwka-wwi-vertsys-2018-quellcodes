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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Mock-Klasse, die von den simulierten Sensoren und Aktoren in der
 * main()-Methode verwendet wird, um die Simulation zu starten. Diese Klasse
 * stellt die Verbindung mit dem MQTT-Broker her, fragt den Sensor regelmäßig ab
 * und leitet die empfangenen Nachrichten an den Sensor weiter.
 */
public class MockRunner implements MqttCallback {

    private SensorActor sensor;
    private MqttClient client;

    public void run(SensorActor s, String name) {
        try {
            sensor = s;

            // Adresse des MQTT-Brokers abfragen
            BufferedReader fromKeyboard = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Sensor: " + name);
            System.out.print("MQTT-Broker [" + Utils.MQTT_BROKER_ADDRESS + "] ");
            String address = fromKeyboard.readLine();

            if (address.trim().isEmpty()) {
                address = Utils.MQTT_BROKER_ADDRESS;
            }

            // Verbindung zum MQTT-Broker herstellen            
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            //options.setUserName("USERNAME");
            //options.setPassword("PASSWORD");

            String clientId = "SmartHome-Device-" + System.currentTimeMillis();

            System.out.println("Client ID: " + clientId);
            System.out.println("Starte Simulation. Drücke ENTER zum Beenden.");
            System.out.println();

            client = new MqttClient(address, clientId);
            client.connect(options);

            // Topic abonnieren und Initialisierungnachricht schicken
            String topic = sensor.getTopicName();
            SensorMessage msg = sensor.init();

            if (topic != null) {
                client.subscribe(topic);
                client.setCallback(this);
            }

            if (topic != null && msg != null) {
                send(topic, msg);
            }

            // Thread starten, der jede Sekunde den aktuellen Sensorwert verschickt
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    try {
                        send(topic, sensor.getCurrentValue());
                    } catch (Exception ex) {
                        Utils.logException(ex);
                    }
                }
            };
            
            Timer timer = new Timer(true);
            timer.scheduleAtFixedRate(task, 0, 1000);

            // Warten, bis das Programm beendet werden soll
            fromKeyboard.readLine();

            msg = sensor.quit();

            if (topic != null && msg != null) {
                send(topic, msg);
            }

            client.disconnect();
            System.exit(0);
        } catch (Exception ex) {
            Utils.logException(ex);
        }
    }

    private void send(String topic, SensorMessage sensorMessage) throws MqttException {
        if (topic != null && sensorMessage != null) {
            byte[] json = sensorMessage.toJson();
            System.out.println("→ " + new String(json, StandardCharsets.UTF_8));

            MqttMessage mqttMessage = new MqttMessage(json);
            mqttMessage.setQos(0);
            client.publish(topic, mqttMessage);
        }
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        if (topic.equals(sensor.getTopicName())) {
            byte[] json = mqttMessage.getPayload();
            SensorMessage sensorMessage = SensorMessage.fromJson(json);

            if (sensorMessage.type.equals(SensorMessageType.COMMAND)) {
                System.out.println("← " + new String(json, StandardCharsets.UTF_8));
            }

            sensor.handleMessage(sensorMessage);
        }
    }

    @Override
    public void connectionLost(Throwable thrwbl) {
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken imdt) {
    }

}
