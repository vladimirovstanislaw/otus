package ru.otus.homework;

public class Main {
    public static void main(String[] args) throws ReflectiveOperationException {
        new TestRunner().runTests(Testable.class.getCanonicalName());
    }
}
