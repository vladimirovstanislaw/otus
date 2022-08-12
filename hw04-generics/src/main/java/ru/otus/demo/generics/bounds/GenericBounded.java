package ru.otus.demo.generics.bounds;

import ru.otus.demo.generics.GenericMethod;
import ru.otus.demo.generics.bounds.entries.Cat;
import ru.otus.demo.generics.bounds.entries.HomeCat;
import ru.otus.demo.generics.bounds.entries.WildCat;

public class GenericBounded<T extends Cat> {
    private T cat;

    public static void main(String[] args) {
        //9  GenericBounded<Animal> not_ok = new GenericBounded<>();
        GenericBounded<Cat> ok1 = new GenericBounded<>();
        GenericBounded<HomeCat> ok2 = new GenericBounded<>();
        GenericBounded<WildCat> ok3 = new GenericBounded<>();
    }

    public void doSmth() {
        cat.getMyau();
    }
}
