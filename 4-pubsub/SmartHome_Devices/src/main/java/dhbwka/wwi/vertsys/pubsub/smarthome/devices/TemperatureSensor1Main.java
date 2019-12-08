/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.pubsub.smarthome.devices;

import dhbwka.wwi.vertsys.pubsub.smarthome.utils.MockRunner;
import dhbwka.wwi.vertsys.pubsub.smarthome.utils.SensorActor;
import dhbwka.wwi.vertsys.pubsub.smarthome.utils.SensorMessage;
import dhbwka.wwi.vertsys.pubsub.smarthome.utils.SensorMessageType;
import dhbwka.wwi.vertsys.pubsub.smarthome.utils.Utils;

/**
 * Mock-Programm, das einen Temperatursensor für den Innenbereich simuliert.
 */
public class TemperatureSensor1Main implements SensorActor {

    public static void main(String[] args) {
        SensorActor sensor = new TemperatureSensor1Main();
        new MockRunner().run(sensor, "Temperatur innen");
    }

    @Override
    public String getTopicName() {
        return Utils.MQTT_TOPIC_NAME + "/Temp1";
    }

    @Override
    public SensorMessage init() throws Exception {
        SensorMessage msg = new SensorMessage();
        msg.type = SensorMessageType.STATUS;
        msg.text = "Temparatursensor 1 ist nun online";
        return msg;
    }

    @Override
    public SensorMessage quit() throws Exception {
        SensorMessage msg = new SensorMessage();
        msg.type = SensorMessageType.STATUS;
        msg.text = "Temparatursensor 1 ist nun offline";
        return msg;
    }

    @Override
    public SensorMessage getCurrentValue() throws Exception {
        double baseTemp = 24.0;
        double corridor = 2.0;
        double noise = 1.0;
        double time = System.currentTimeMillis() / 1000 / 60;

        SensorMessage msg = new SensorMessage();
        msg.type = SensorMessageType.VALUE;
        msg.number = baseTemp + (Math.sin(time) * corridor) + ((Math.random() * noise) - noise / 2);
        return msg;
    }

    @Override
    public void handleMessage(SensorMessage sensorMessage) throws Exception {
    }

}
