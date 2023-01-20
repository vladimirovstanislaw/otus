package ru.otus.protobuf.hw.datamodel;

public class ServerMessage {
    private final int currentValue;

    public ServerMessage(int currentValue) {
        this.currentValue = currentValue;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    @Override
    public String toString() {
        return "ServerMessage{" +
                "currentValue=" + currentValue +
                '}';
    }
}
