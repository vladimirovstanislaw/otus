package ru.otus.reflection;


import java.lang.reflect.*;

public class ReflectionPrivate {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        Class<DemoClass> clazz = DemoClass.class;
        System.out.println("Class Name:" + clazz.getSimpleName());

        Constructor<DemoClass> constructor = clazz.getConstructor(String.class);
        DemoClass object = constructor.newInstance("testVal");

        System.out.println("------private method execution");
        Method privateMethod = clazz.getDeclaredMethod("privateMethod");
        privateMethod.setAccessible(true);
        privateMethod.invoke(object);

        System.out.println("--- changing private field:");
        Field privatefield = clazz.getDeclaredField("valuePrivate");
        privatefield.setAccessible(true); //Делаем как бы "public"
        privatefield.set(object, "privateValueChanged");
        System.out.println("changed value:" + privatefield.get(object));
    }
}
