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
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Mock-Programm, das einen Rolladen simuliert.
 */
public class WindowBlindsMain implements SensorActor {

    // Achtung! Nur mit Getter und Setter verwenden, wenn aus mehreren
    // Threads auf den Wert zugegriffen wird, da die Setter und Getter
    // "synchronized" sind.
    private double position = 0.0;

    public static void main(String[] args) {
        SensorActor sensor = new WindowBlindsMain();
        new MockRunner().run(sensor, "Rolladen");
    }

    @Override
    public String getTopicName() {
        return Utils.MQTT_TOPIC_NAME + "/WindowBlinds";
    }

    @Override
    public SensorMessage init() throws Exception {
        SensorMessage msg = new SensorMessage();
        msg.type = SensorMessageType.STATUS;
        msg.text = "Rolladen ist nun online";
        return msg;
    }

    @Override
    public SensorMessage quit() throws Exception {
        SensorMessage msg = new SensorMessage();
        msg.type = SensorMessageType.STATUS;
        msg.text = "Rolladen ist nun offline";
        return msg;
    }

    @Override
    public SensorMessage getCurrentValue() throws Exception {
        SensorMessage msg = new SensorMessage();
        msg.type = SensorMessageType.VALUE;
        msg.number = this.getPosition();
        return msg;
    }

    @Override
    public void handleMessage(SensorMessage sensorMessage) throws Exception {
        if (sensorMessage.type.equals(SensorMessageType.COMMAND)) {
            switch (sensorMessage.text.toLowerCase()) {
                case "up":
                    //new MoveBlindsThread(this, 0.0).start();
                    this.moveUp();
                    break;
                case "down":
                    //new MoveBlindsThread(this, 1.0).start();
                    this.moveDown();
                    break;
            }
        }
    }

    public synchronized double getPosition() {
        return this.position;
    }

    public synchronized void setPosition(double position) {
        this.position = position;
    }

    /**
     * Sanftes Hochfahren des Rollos über 5 Sekunden simulieren.
     */
    private void moveUp() {
        double targetPosition = 0.0;
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);

        Runnable interpolate = () -> {
            double currentPosition = this.getPosition();

            if (currentPosition <= targetPosition) {
                this.setPosition(targetPosition);
                executor.shutdown();
            } else {
                this.setPosition(currentPosition -= 0.02);
            }
        };

        executor.scheduleWithFixedDelay(interpolate, 0, 100, TimeUnit.MILLISECONDS);
    }

    /**
     * Sanftes Herunterfahren des Rollos über 5 Sekunden simulieren.
     */
    private void moveDown() {
        double targetPosition = 1.0;
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);

        Runnable interpolate = () -> {
            double currentPosition = this.getPosition();

            if (currentPosition >= targetPosition) {
                this.setPosition(targetPosition);
                executor.shutdown();
            } else {
                this.setPosition(currentPosition += 0.02);
            }
        };

        executor.scheduleWithFixedDelay(interpolate, 0, 100, TimeUnit.MILLISECONDS);
    }
}
