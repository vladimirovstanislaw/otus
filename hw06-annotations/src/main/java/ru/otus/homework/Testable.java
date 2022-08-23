package ru.otus.homework;

public class Testable {
    //happy path, without exception, all works fine
    @Before
    public void before1() {
        System.out.println("Before test");
        throw new RuntimeException("Before test exception");
    }

    @Before
    public void before2() {
        System.out.println("Before test2");
    }

    @Test
    public void test() {
        System.out.println("Test");
    }

    @After
    public void after() {
        System.out.println("After test");
    }

}
