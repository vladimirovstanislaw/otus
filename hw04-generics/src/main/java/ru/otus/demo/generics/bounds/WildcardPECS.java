package ru.otus.demo.generics.bounds;

import ru.otus.demo.generics.bounds.entries.Animal;
import ru.otus.demo.generics.bounds.entries.Cat;
import ru.otus.demo.generics.bounds.entries.HomeCat;
import ru.otus.demo.generics.bounds.entries.WildCat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WildcardPECS {
    public static void main(String[] args) {
        System.out.println("============= List<Animal> =============");
        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Animal());
        animalList.add(new Cat());
        //printProducer(animalList); - не можем, т.к. Animal родитель Cat и не является его потомком.
        printConsumer(animalList);

        System.out.println("============= List<Cat>  =============");
        List<Cat> catList = new ArrayList<>();
        catList.add(new Cat());
        printProducer(catList);
        printConsumer(catList);

        System.out.println("============= List<HomeCat> =============");
        List<HomeCat> homeCatList = new ArrayList<>();
        homeCatList.add(new HomeCat("homeCat"));
        printProducer(homeCatList);
        //printConsumer(homeCatList); //9 - т.к. homeCat - наследник Cat, а оперделение метода ? super Cat, что означает,
        //                                что мы можем положить либо кошку либо её родителей.


    }

    private static void printProducer(List<? extends Cat> catList) {
//        catList.add(new Object()); //Ошибка
//        catList.add(new Animal()); //Ошибка
//        catList.add(new Cat()) //Ошибка
//        catList.add(new HomeCat("f")); //Ошибка

        List<Animal> catList2 = Arrays.asList(new Cat(), new HomeCat("d"));
        List<? extends Cat> catList3 = new ArrayList<>();

        //catList.addAll(catList2); //Ошибка

        catList.forEach(System.out::println);
    }

    private static void printConsumer(List<? super Cat> catList) {
        //        catList.add(new Object());// Ошибка
        //catList.add(new Animal());// Ошибка
        catList.add(new Cat());
        catList.add(new HomeCat("noName"));//Мы можем вызвать этот метод на animalList и на catList, но не можем на homeCatList
        //Т.к. мы можем добавить кошку и её наследников HomeCat/WildCat, но при этом не можем добавить Animal(?).
        //Из-за <? super Cat> - нам дает возможность модификации - мы знаем что пришедший сюда список гарантированно поддерживает кошку
        //  и соотвественно мы можем свободно добавлять её наследников не ломая при этом корректное поведение списка.
        catList.add(new WildCat("noName"));


        Object item = catList.get(0);//get() возвращает самый верхний класс в иерархии - Object, именно поэтому
        // используется приведение и поэтому мы можем вызвать ниже getMyau()
        System.out.println("item from the list:" + item);

        catList.forEach(System.out::println);
    }
}
