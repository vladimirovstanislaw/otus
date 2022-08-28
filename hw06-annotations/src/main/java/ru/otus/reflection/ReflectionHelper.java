package ru.otus.reflection;

import java.util.Arrays;

public class ReflectionHelper {
    private ReflectionHelper() {
    }

    public static Object getFieldValue(Object o, String name) {
        try {
            var field = o.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(o);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void setFieldValue(Object o, String name, Object value) {
        try {
            var field = o.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(o, value);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Object callMethod(Object object, String name, Object... args) {
        try {
            var method = object.getClass().getMethod(name, toClasses(args));
            method.setAccessible(true);
            return method.invoke(object, args);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static <T> T instantiate(Class<T> type, Object... args) {
        try {
            if (args.length == 0) {
                return type.getDeclaredConstructor().newInstance();
            } else {
                Class<?>[] classes = toClasses(args);
                return type.getDeclaredConstructor(classes).newInstance(args);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    //Надо потестить, что эта штука вообще делает
    public static Class<?>[] toClasses(Object[] args) {
        return Arrays.stream(args).map((object) -> object.getClass()).toArray(Class<?>[]::new);
    }
}
