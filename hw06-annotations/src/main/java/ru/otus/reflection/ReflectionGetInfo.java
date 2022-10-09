package ru.otus.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class ReflectionGetInfo {
    public static void main(String[] args) throws NoSuchMethodException {
        Class<DemoClass> clazz = DemoClass.class;
        System.out.println("Class name:" + clazz.getSimpleName());

        Constructor<?>[] constructors = clazz.getConstructors();
        System.out.println("----------constructors");
        System.out.println(Arrays.toString(constructors));

        Method[] methodsPublic = clazz.getMethods();
        System.out.println("----------public methods");
        Arrays.stream(methodsPublic).forEach(method -> System.out.println(method.getName()));

        Method[] methodsAll = clazz.getDeclaredMethods();
        System.out.println("-----all methods");
        Arrays.stream(methodsAll).forEach(method -> System.out.println(method.getName()));

        System.out.println("------public fields");
        Field[] fieldsPublic = clazz.getFields();
        Arrays.stream(fieldsPublic).forEach(field -> System.out.println(field));

        System.out.println("------all fields");
        Field[] allFields = clazz.getDeclaredFields();
        Arrays.stream(allFields).forEach(System.out::println);

        System.out.println("------annotations ");
        Method annotatedMethod = clazz.getMethod("toString");
        Annotation[] annotations = annotatedMethod.getDeclaredAnnotations();
        System.out.println(Arrays.toString(annotations));

        Client client = new Client(1l, "nn");
        Field[] allFieldsClient = Client.class.getDeclaredFields();
        for (Field field : allFieldsClient) {
            System.out.println(field.getType());
        }

    }

    private static class Client {

        private Long id;
        private String name;

        public Client() {
        }

        public Client(String name) {
            this.id = null;
            this.name = name;
        }

        public Client(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Client{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

    }

}
