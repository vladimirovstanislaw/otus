package ru.otus.homework;

public class Testable {
    //happy path, without exception, all works fine
    @Before
    public void before() {
        System.out.println("Before test");
    }

    @Test
    public void test() {
        System.out.println("Test");
    }

    @After
    public void after() {
        System.out.println("After test");
    }

    //happy path, without exception, all works fine

}
