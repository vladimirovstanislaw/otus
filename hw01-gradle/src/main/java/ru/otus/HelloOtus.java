package ru.otus;

import com.google.common.collect.Lists;

import java.util.function.Consumer;

public class HelloOtus {
    public static void main(String[] args) {
        Lists.newArrayList("Hola ", "otus ", "first ", "homework!").stream().forEach(System.out::print);
    }

    public static void say() {
        Lists.newArrayList("API-gradle ", "use!").stream().forEach(System.out::print);
    }
}
