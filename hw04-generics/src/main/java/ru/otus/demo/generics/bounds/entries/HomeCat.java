package ru.otus.demo.generics.bounds.entries;

public class HomeCat extends Cat {
    private String name;

    public HomeCat(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "HomeCat, name=" + name;
    }
}

