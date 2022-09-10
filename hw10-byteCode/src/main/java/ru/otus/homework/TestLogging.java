package ru.otus.homework;

class TestLogging implements TestLoggingInterface {


    @Log
    public void calculation(int... param) {
        int result = 0;
        for (int i : param) {
            result += i;
        }
        System.out.println("result: " + result);
    }

    public void anotherCalculation() {
        System.out.println("Another calculation");
    }
}
