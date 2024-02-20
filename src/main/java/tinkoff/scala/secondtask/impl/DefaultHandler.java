package tinkoff.scala.secondtask.impl;

import java.time.Duration;

import tinkoff.scala.secondtask.Client;
import tinkoff.scala.secondtask.Handler;

public class DefaultHandler implements Handler {

    private final Client client;

    public DefaultHandler(Client client) {
        this.client = client;
    }

    @Override
    public Duration timeout() {
        // configurable
        return null;
    }

    @Override
    public void performOperation() {
        var event = client.readData();
        event.recipients().parallelStream().forEach(address -> {
            var result = client.sendData(address, event.payload());
            switch (result) {
                case ACCEPTED -> event.recipients().remove(address);
                case REJECTED -> {
                    var thread = new Thread(() -> client.sendData(address, event.payload()));
                    try {
                        thread.wait(timeout().toMillis());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    thread.run();
                }
            }
        });
    }
}
