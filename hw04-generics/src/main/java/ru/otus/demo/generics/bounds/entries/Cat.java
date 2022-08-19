package ru.otus.demo.generics.bounds.entries;

public class Cat extends Animal {

    @Override
    public String toString() {
        return "Cat";
    }

    public String getMyau() {
        return "Myauu";
    }

    @Override
    public String getMilk() {
        return "Cat milk";
    }
}
