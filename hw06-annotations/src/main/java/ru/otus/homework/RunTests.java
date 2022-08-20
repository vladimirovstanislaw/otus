package ru.otus.homework;

import ru.otus.reflection.SimpleAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class RunTests {
    public RunTests() {
    }

    public void runTests(String className) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
        System.out.println("Testing class '" + clazz.getSimpleName() + "'");

        Method[] methods = clazz.getMethods();
        //достаем методы с аннотацией @Test
        List<Method> testMethods = getMethodsWithСertainAnnotation(methods, Test.class);

        //достаем методы с аннотацией @Before
        List<Method> beforeMethods = getMethodsWithСertainAnnotation(methods, Before.class);

        //достаем методы с аннотацией @After
        List<Method> afterMethods = getMethodsWithСertainAnnotation(methods, After.class);


        //создаем последовательности тестирования - каждому методу @Test предшествуют все доступные в классе @Before и @After
        //почему я так сделал - в методе getMethods() указано, что "The elements in the returned array are not sorted and are not in any particular order",

        for (Method testMethod : testMethods) {
            //Создаем под каждый @Test отдельный инстанс

            //выполняем все @Before
            for(Method beforeMethod: beforeMethods){

            }
            //выполняем собственно @Test


            //выполняем все @After
            for(Method afterMethod: afterMethods){

            }
        }
    }

    private ArrayList<Method> getMethodsWithСertainAnnotation(Method[] methods, Class<? extends Annotation> annotationClazz) throws ClassNotFoundException {
        ArrayList<Method> listMethods = new ArrayList<>();
        System.out.println("-----------достаем методы с аннотацией @Test");
        for (Method method : methods) {
            System.out.println("-----------------------------------Метод:" + method.toString());
            if (method.isAnnotationPresent((Class<? extends Annotation>) Class.forName(annotationClazz.getCanonicalName()))) {
                listMethods.add(method);
            }
        }
        return listMethods;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        RunTests runTests = new RunTests();
        runTests.runTests(Testable.class.getCanonicalName());
    }
}
