package ru.otus.demo.generics;

public class GenericMethod<K, V> {
    public static void main(String[] args) {
        GenericMethod genericMethod = new GenericMethod();
        genericMethod.print(1, "value1");
        genericMethod.print(2, "value2");
        genericMethod.print("3", "value3");
    }

    private <K, V> void print(K key, V val) {//Если generic-и указаны и в классе и в методе и при этом сами нарушаем контракт при использованни метода, то что бы мы не запихивали в метод - это всё будет приводиться к Object.
        System.out.println("Key: " + key + " Val=" + val);
    }

}
