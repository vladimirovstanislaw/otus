package ru.otus.demo.generics.bounds;

import ru.otus.demo.generics.bounds.entries.Animal;
import ru.otus.demo.generics.bounds.entries.Cat;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Wildcard_2 {
    public static void main(String[] args) {

        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Animal());
        printWild(animalList);

        List<Cat> catList = new ArrayList<>();
        catList.add(new Cat());
        //printWild(catList);
        printObj(catList);
        for (Cat cat : catList) {
            if (cat instanceof Cat) {
                System.out.println(cat.getMyau());
            }
        }
        //левый тип данных
        List<String> stringList = new ArrayList<>();
        stringList.add("подкидыш");
        printWild(stringList);
        //
        //Какая то магия
        List voidList = new ArrayList<>();
        voidList.add(LocalTime.now());
        printWild(voidList);
        printObj(voidList);

    }

    public static void printWild(List<?> animalList) {
        //animalList.add("внезапность"); 9 - т.к. видимо под часть PE От правила PECS попадает так же и ?, т.е. простой generic
        animalList.forEach(System.out::println);

    }

    public static void printObj(List animalList) {// ответственность полностью отдается разработчкику
        //animalList.add("внезапность obj"); - возникает ClassCastException
        animalList.forEach(System.out::println);
    }
}
