package ru.otus.protobuf.hw.datamodel;

public class InitialClientMessage {
    private final int firstValue;
    private final int lastValue;

    public InitialClientMessage(int firstValue, int lastValue) {
        this.firstValue = firstValue;
        this.lastValue = lastValue;
    }

    public int getFirstValue() {
        return firstValue;
    }

    public int getLastValue() {
        return lastValue;
    }

    @Override
    public String toString() {
        return "InitialClientMessage{" +
                "firstValue=" + firstValue +
                ", lastValue=" + lastValue +
                '}';
    }
}
