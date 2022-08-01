package ru.otus.demo.generics.bounds;

import ru.otus.demo.generics.bounds.entries.Animal;
import ru.otus.demo.generics.bounds.entries.Cat;

import java.util.ArrayList;
import java.util.List;

public class GenericInheritance {
    public static void main(String[] args) {
        Animal cat = new Cat();
        List<Cat> catList = new ArrayList<>(1);
        //9 List<Animal> animalList = catList; - Class Cat - наследник от Animal, но List<Cat> не наследник от List<Animal> . Наследуются индивидуумы, а не их группы.

    }
}
