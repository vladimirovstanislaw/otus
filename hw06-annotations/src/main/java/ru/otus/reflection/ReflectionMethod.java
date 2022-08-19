package ru.otus.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class ReflectionMethod {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<DemoClass> clazz = DemoClass.class;
        System.out.println("Class name: " + clazz.getSimpleName());

        Method method = clazz.getMethod("toString");

        System.out.println("-------annotation");
        Annotation[] annotations = method.getDeclaredAnnotations();
        System.out.println(Arrays.toString(annotations));

        System.out.println("-------modifiers");
        int modifiers = method.getModifiers();
        System.out.println("modifiers: " + Modifier.toString(modifiers));
        System.out.println("isPublic:" + Modifier.isPublic(modifiers));
        System.out.println("isFinal:" + Modifier.isFinal(modifiers));
        System.out.println("isStatic:" + Modifier.isStatic(modifiers));

        System.out.println("-----execition");
        Object result = method.invoke(new DemoClass("demoVal"));
        System.out.println("result: " + result);
    }
}
