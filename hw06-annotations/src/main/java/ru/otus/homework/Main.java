package ru.otus.homework;

public class Main {
    public static void main(String[] args) throws ReflectiveOperationException {
        TestRunner runTests = new TestRunner();
        runTests.runTests(Testable.class.getCanonicalName());

    }
}
