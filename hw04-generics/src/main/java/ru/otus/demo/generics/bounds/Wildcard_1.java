package ru.otus.demo.generics.bounds;

import ru.otus.demo.generics.bounds.entries.Animal;
import ru.otus.demo.generics.bounds.entries.Cat;
import ru.otus.demo.generics.bounds.entries.HomeCat;
import ru.otus.demo.generics.bounds.entries.WildCat;

import javax.print.attribute.standard.PrinterMoreInfoManufacturer;
import java.util.ArrayList;
import java.util.List;

public class Wildcard_1 {
    public static void main(String[] args) {
        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Animal());
        animalList.add(new HomeCat("Animal - new milk"));

        //  print(animalList);
        printWild(animalList);

        List<Cat> catList = new ArrayList<>();
        catList.add(new Cat());
        catList.add(new HomeCat("Барсик"));
        catList.add(new WildCat("Багира"));
        // 9 catList.add(new Animal());

        List<HomeCat> homeCatList = new ArrayList<>();
        //printWild(homeCatList);
        //print(catList);
        //printWild(catList);
    }

    private static void print(List<Animal> animalList) {
        animalList.forEach(System.out::println);
    }

    //                               (List<Animal> animalList - так нельзя, т.к. для homeCatList и catList будет ошибка типизации.
    private static void printWild(List<? extends Animal> animalList//список наследников от Animals
    ) {
        animalList.forEach(System.out::println);
        //animalList.add(new Cat()); - Ошибка компмилятора, т.к. в метод мог прийти инстанс List<WildCat>,
        //                                                   и добавление Cat приведет к непредвиденным последствиям,
        //                                                   поэтому при Extends нельзя модифицировать список.
        //                                                   Это PE часть принципа PECS - Producer extends,
        //                                                   Producer в данной каннотации - это коллекция, которая produc-ит
        //                                                   нам элементы, она их предоставляет/производит.
        //
        for (Animal item : animalList) {
            System.out.println(item.getMilk());
        }
    }
}
