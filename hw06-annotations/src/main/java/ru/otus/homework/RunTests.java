package ru.otus.homework;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class RunTests {
    public RunTests() {
    }

    public void runTests(String className) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        int successfulTests = 0;
        int failedTests = 0;
        Class<?> clazz = Class.forName(className);
        System.out.println("Testing class '" + clazz.getSimpleName() + "'");

        Method[] methods = clazz.getMethods();
        //достаем методы с аннотацией @Test
        List<Method> testMethods = getMethodsWithAnnotation(methods, Test.class);

        //достаем методы с аннотацией @Before
        List<Method> beforeMethods = getMethodsWithAnnotation(methods, Before.class);

        //достаем методы с аннотацией @After
        List<Method> afterMethods = getMethodsWithAnnotation(methods, After.class);


        //создаем последовательности тестирования - каждому методу @Test предшествуют все доступные в классе @Before и @After
        //почему я так сделал - в методе getMethods() указано, что "The elements in the returned array are not sorted and are not in any particular order",

        for (Method testMethod : testMethods) {
            //Создаем под каждый @Test отдельный инстанс
            var constructor = clazz.getConstructor();
            var object = constructor.newInstance();


            //выполняем все @Before
            for (Method beforeMethod : beforeMethods) {
                try {
                    beforeMethod.invoke(object);
                    successfulTests++;
                } catch (Exception ex) {
                    failedTests++;
                }
            }
            //выполняем собственно @Test
            try {
                testMethod.invoke(object);
                successfulTests++;
            } catch (Exception ex) {
                failedTests++;
            }

            //выполняем все @After
            for (Method afterMethod : afterMethods) {
                try {
                    afterMethod.invoke(object);
                    successfulTests++;
                } catch (Exception ex) {
                    failedTests++;
                }
            }
        }
        System.out.println("Whole count of tests = " + (successfulTests + failedTests));
        System.out.println(
                "Count of successfully passed tests = " + successfulTests);
        System.out.println("Count of failed tests = " + failedTests);
    }

    private ArrayList<Method> getMethodsWithAnnotation(Method[] methods, Class<? extends Annotation> annotationClazz) throws ClassNotFoundException {
        ArrayList<Method> listMethods = new ArrayList<>();
        //System.out.println("-----------достаем методы с аннотацией @Test");
        for (Method method : methods) {
            //System.out.println("-----------------------------------Метод:" + method.toString());
            if (method.isAnnotationPresent((Class<? extends Annotation>) Class.forName(annotationClazz.getCanonicalName()))) {
                listMethods.add(method);
            }
        }
        return listMethods;
    }

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        RunTests runTests = new RunTests();
        runTests.runTests(Testable.class.getCanonicalName());
    }
}
