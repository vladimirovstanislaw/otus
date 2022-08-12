package ru.otus;

public class Computer {
    int year;
    String color;

    public Computer() {
    }

    public Computer(int year, String color) {
        this.year = year;
        this.color = color;
    }

    public static void turnOnPc(Computer c) {
        System.out.println("Instance turn on.");
    }

    public static void turnOnPc(Object o) {
    }
}
