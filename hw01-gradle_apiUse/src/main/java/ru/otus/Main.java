package ru.otus;

import com.google.common.collect.Lists;

public class Main {
    public static void main(String[] args) {
        Lists.newArrayList("Hola ", "otus ", "first ", "homework!").stream().forEach(System.out::print);
    }
}