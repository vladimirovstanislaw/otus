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

    @Log
    public void calculation() {
        System.out.println("void");
    }

    @Log
    public void calculation(String h) {
        System.out.println("S");
    }

    @Log
    public void calculation(String s1, String s2, int i) {
        System.out.println("S,S,i");
    }

    public void anotherCalculation() {
        System.out.println("Another calculation");
    }
}
