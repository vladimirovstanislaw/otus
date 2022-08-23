package ru.otus.homework;

import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

public class TestRunner {
    public TestRunner() {
    }

    public void runTests(String className) throws ReflectiveOperationException {
        AtomicInteger successfulTests = new AtomicInteger(0);
        AtomicInteger failedTests = new AtomicInteger(0);
        AtomicInteger successfulAfter = new AtomicInteger(0);
        AtomicInteger failedAfter = new AtomicInteger(0);
        AtomicInteger successfulBefore = new AtomicInteger(0);
        AtomicInteger failedBefore = new AtomicInteger(0);

        Class<?> clazz = Class.forName(className);
        System.out.println("Testing class '" + clazz.getSimpleName() + "'");
        TestingContext testingContext = new TestingContext(clazz);

        //создаем последовательности тестирования - каждому методу @Test предшествуют все доступные в классе @Before и @After
        //почему я так сделал - в методе getMethods() указано, что "The elements in the returned array are not sorted and are not in any particular order",
        System.out.println("Count of @Test methods: " + testingContext.getTestMethods().size());
        System.out.println("--------------------Methods outputs--------------------");
        for (Method testMethod : testingContext.getTestMethods()) {
            //Создаем под каждый @Test отдельный инстанс
            var object = testingContext.instantiate();

            //выполняем все @Before
            for (Method beforeMethod : testingContext.getBeforeMethods()) {
                runMethod(beforeMethod, object, successfulBefore, failedBefore);
            }

            if (failedBefore.get() == 0) {
                //выполняем @Test
                runMethod(testMethod, object, successfulTests, failedTests);
            } else {
                failedTests.incrementAndGet();
            }

            //выполняем все @After
            for (Method afterMethod : testingContext.getAfterMethods()) {
                runMethod(afterMethod, object, successfulAfter, failedAfter);
            }
        }
        System.out.println("--------------------Methods outputs--------------------");
        System.out.println("@Test statistic:");
        System.out.println("Whole count of tests = " + testingContext.getTestMethods().size());
        System.out.println("Count of successfully passed tests = " + successfulTests.intValue());
        System.out.println("Count of failed tests = " + failedTests.intValue());
        //before
        System.out.println("@Before  statistic:");
        System.out.println("Whole count of @Before methods = " + testingContext.getBeforeMethods().size());
        System.out.println("Count of successfully passed @Before methods = " + successfulBefore.intValue());
        System.out.println("Count of failed @Before methods = " + failedBefore.intValue());
        //after
        System.out.println("@After statistic:");
        System.out.println("Whole count of @After methods = " + testingContext.getAfterMethods().size());
        System.out.println("Count of successfully passed @After methods = " + successfulAfter.intValue());
        System.out.println("Count of failed @After methods = " + failedAfter.intValue());

    }

    private void runMethod(Method method, Object o, AtomicInteger successfulTests, AtomicInteger failedTests) {
        try {
            method.invoke(o);
            successfulTests.incrementAndGet();
        } catch (Exception ex) {
            failedTests.incrementAndGet();
        }
    }


}
