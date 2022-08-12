package ru.otus.demo.generics;

import java.util.HashMap;
import java.util.Map;

public class GenericClass<K, V> {
    private final Map<K, V> map = new HashMap<>();

    public static void main(String[] args) {
        GenericClass<Integer, String> genericClass = new GenericClass<Integer, String>();
        genericClass.putVal(1, "data1");
        genericClass.print();
    }

    public void putVal(K key, V val) {
        map.put(key, val);
    }

    public void print() {
        map.forEach((key, val) -> System.out.println("Key: " + key + " Val=" + val));
    }
}
