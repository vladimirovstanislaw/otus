package ru.otus;

import java.util.Arrays;
import java.util.List;

public class Macbook extends Computer {
    public Macbook(int year, String color) {
        this.year = year;
        this.color = color;
    }
    public void someCalc() {

    }
    public static void main(String[] args) {
        Computer c1 = new Computer(2015, "white");
        Computer c2 = new Macbook(2009, "black");
        List inventory = Arrays.asList(c1, c2);
        inventory.forEach(Computer::turnOnPc);
    }
}
