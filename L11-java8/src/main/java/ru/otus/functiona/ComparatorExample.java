package ru.otus.functiona;

import org.checkerframework.org.plumelib.util.RegexUtil;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

public class ComparatorExample {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("AA", "BBB", "C");
        //Создание инстанса
        Collections.sort(list, new MyComparator());

        //анонимный класс
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        //lambda
        Collections.sort(list, (String s1, String s2) -> {
            var file = new File("jasd");
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            return s1.length() - s2.length();
        });
        //типы можно вывести - т.к. в коллекции уже они есть
        Collections.sort(list, (s1, s2) -> {
            return s1.length() - s2.length();
        });

        Collections.sort(list, Comparator.comparingInt(s -> s.length()));// логика зашита уже в Comparator, мы предоставляем только параметр на основе кт будем сравнивать.
        Collections.sort(list, Comparator.comparingInt(String::length));//пришло из C - вызываем функцию, кт надо вызвать, когда работает компаратор.
        list.stream().forEach(System.out::println);
    }

    static class MyComparator implements Comparator<String> {

        @Override
        public int compare(@NotNull String o1, @NotNull String o2) {
            return o1.length() - o2.length();
        }
    }
}
