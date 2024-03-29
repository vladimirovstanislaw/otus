package ru.otus.reflection;

public class Auseful {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        var primitiveString = String.class.isPrimitive();
        var primitiveInt = int.class.isPrimitive();
        System.out.println("primitiveString: " + primitiveString + "  primitiveInt: " + primitiveInt);

        int[] arr = {1, 2};
        var isArray = arr.getClass().isArray();
        Class<?> componentArr = arr.getClass().getComponentType();
        System.out.println("isArray:" + isArray + " componentArr: " + componentArr);

        Class<?> string = Class.forName("java.lang.String");
        var isIterableString = Iterable.class.isAssignableFrom(string);

        Class<?> list = Class.forName("java.util.ArrayList");
        var isIterableList = Iterable.class.isAssignableFrom(list);
        System.out.println("isIterableString:" + isIterableString + ", isIterableList:" + isIterableList);

        var hasAnnotation = DemoClassInt.class.getMethod("getValuePrivate").isAnnotationPresent(SimpleAnnotation.class);
        System.out.println("hasAnnotation: " + hasAnnotation);

        var listClasses = list.getClasses();
        var supperListClass = list.getSuperclass();
        var listInt = list.getInterfaces();
        System.out.println("listClasses" + listClasses);
        System.out.println("supperListClass" + supperListClass);

    }
}
