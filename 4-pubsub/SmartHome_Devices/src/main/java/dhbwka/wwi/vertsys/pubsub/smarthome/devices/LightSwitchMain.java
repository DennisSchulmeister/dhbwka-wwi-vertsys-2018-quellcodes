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
 * Mock-Programm, das einen Lichtschalter simuliert.
 */
public class LightSwitchMain implements SensorActor {

    private boolean on = false;

    public static void main(String[] args) {
        SensorActor sensor = new LightSwitchMain();
        new MockRunner().run(sensor, "Lichtschalter");
    }

    @Override
    public String getTopicName() {
        return Utils.MQTT_TOPIC_NAME + "/LightSwitch";
    }

    @Override
    public SensorMessage init() throws Exception {
        SensorMessage msg = new SensorMessage();
        msg.type = SensorMessageType.STATUS;
        msg.text = "Lichtschalter ist nun online";
        return msg;
    }

    @Override
    public SensorMessage quit() throws Exception {
        SensorMessage msg = new SensorMessage();
        msg.type = SensorMessageType.STATUS;
        msg.text = "Lichtschalter ist nun offline";
        return msg;
    }

    @Override
    public SensorMessage getCurrentValue() throws Exception {
        SensorMessage msg = new SensorMessage();
        msg.type = SensorMessageType.VALUE;
        msg.text = this.on ? "on" : "off";
        return msg;
    }

    @Override
    public void handleMessage(SensorMessage sensorMessage) throws Exception {
        if (sensorMessage.type.equals(SensorMessageType.COMMAND)) {
            switch (sensorMessage.text.toLowerCase()) {
                case "toggle":
                    this.on = !this.on;
                    break;
                case "on":
                    this.on = true;
                    break;
                case "off":
                    this.on = false;
                    break;
            }
        }
    }

}
