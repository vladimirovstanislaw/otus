package ru.otus.homework;

class Demo {
    public static void main(String[] args) {
        TestLoggingInterface testLogging = ProxyIoc.createLogging();
//        testLogging.calculation();
        testLogging.calculation(1);
//        testLogging.calculation(1, 2);
//        testLogging.anotherCalculation();

    }
}
