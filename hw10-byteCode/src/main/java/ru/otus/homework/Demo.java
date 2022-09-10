package ru.otus.homework;

import java.util.Arrays;

class Demo {
    public static void main(String[] args) {
        TestLoggingInterface testLogging = ProxyIoc.createLogging();
        testLogging.calculation();
        testLogging.calculation(1);
        testLogging.calculation(1, 2);
        testLogging.calculation(1, 2, 3);
        testLogging.anotherCalculation();

    }
}
