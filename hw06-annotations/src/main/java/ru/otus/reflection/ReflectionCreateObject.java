package ru.otus.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ReflectionCreateObject {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<DemoClass> clazz = DemoClass.class;
        System.out.println("Class Name:" + clazz.getSimpleName());

        Constructor<?>[] constructors = clazz.getConstructors();
        System.out.println("--- constructors:");
        System.out.println(Arrays.toString(constructors));

        System.out.println("------ creating new object:");
        Constructor<DemoClass> classConstructor = clazz.getConstructor(String.class);
        DemoClass object = classConstructor.newInstance("testVal");
        System.out.println("value:" + object.getValuePrivate());
    }
}
