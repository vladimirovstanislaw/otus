package ru.otus.homework.utils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class TestRunner {
    public TestRunner() {
    }

    public void runTests(String className) throws ReflectiveOperationException {
        int failedTests = 0;
        int failedAfter = 0;
        int failedBefore = 0;

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
            failedBefore = runMethods(testingContext.getBeforeMethods(), object);


            if (failedBefore == 0) {
                //выполняем @Test
                failedTests = runMethods(Arrays.asList(testMethod), object);
            } else{
                failedTests++;
            }

            //выполняем все @After
            failedAfter = runMethods(testingContext.getAfterMethods(), object);

        }
        System.out.println("--------------------Methods outputs--------------------");
        System.out.println("@Test statistic:");
        System.out.println("Whole count of tests = " + testingContext.getTestMethods().size());
        System.out.println("Count of successfully passed tests = " + (testingContext.getTestMethods().size() - failedTests));
        System.out.println("Count of failed tests = " + failedTests);
        //before
        System.out.println("@Before  statistic:");
        System.out.println("Whole count of @Before methods = " + testingContext.getBeforeMethods().size());
        System.out.println("Count of successfully passed @Before methods = " + (testingContext.getBeforeMethods().size() - failedBefore));
        System.out.println("Count of failed @Before methods = " + failedBefore);
        //after
        System.out.println("@After statistic:");
        System.out.println("Whole count of @After methods = " + testingContext.getAfterMethods().size());
        System.out.println("Count of successfully passed @After methods = " + (testingContext.getAfterMethods().size() - failedAfter));
        System.out.println("Count of failed @After methods = " + failedAfter);

    }

    private int runMethods(List<Method> methods, Object o) {
        int failed = 0;
        for (Method method : methods) {
            try {
                method.invoke(o);
            } catch (Exception ex) {
                failed++;
            }
        }
        return failed;
    }


}
