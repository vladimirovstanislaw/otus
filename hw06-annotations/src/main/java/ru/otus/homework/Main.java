package ru.otus.homework;

import ru.otus.homework.utils.TestRunner;

public class Main {
    public static void main(String[] args) throws ReflectiveOperationException {
        new TestRunner().runTests(Testable.class.getCanonicalName());
    }
}
