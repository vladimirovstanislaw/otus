package ru.otus.reflection;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ReflectionArray {
    class Inner {

        private String id;

        private static String idPrefix = "Inner_";

        Inner(String id) {
            this.id = idPrefix + id;
        }

        static class StaticClass {
        }

        record Point(int x, int y) {
        }
    }
    private final HashMap<String, String> map = new HashMap<>();

    public static void main(String[] args) {
        int[] arrayInt = new int[0];
        Class<? extends int[]> clazz = arrayInt.getClass();
        System.out.println("isArray: " + clazz.isArray());
        System.out.println("TypeName:" + clazz.getTypeName());

        Class<?> componentType = clazz.getComponentType();
        System.out.println("componentType: " + componentType);

        System.out.println("creating array");

        float[] arrayFloat = (float[]) Array.newInstance(float.class, 5);
        System.out.println("length: " + arrayFloat.length);
        System.out.println("created TypeName:" + arrayFloat.getClass().getTypeName());
        ReflectionArray reflectionArray = new ReflectionArray();
        reflectionArray.map.put("ads", "asd");
        for (Map.Entry<String, String> entry : reflectionArray.map.entrySet()) {

        }
    }
}
