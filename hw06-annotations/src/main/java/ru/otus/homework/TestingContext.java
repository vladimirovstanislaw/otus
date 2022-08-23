package ru.otus.homework;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestingContext {
    private final Class<?> clazz;
    private List<Method> testMethods;
    private List<Method> beforeMethods;
    private List<Method> afterMethods;

    public TestingContext(Class<?> clazz) throws ReflectiveOperationException {
        this.clazz = clazz;
        Method[] methods = this.clazz.getMethods();
        //достаем методы с аннотацией @Test
        this.testMethods = getMethodsWithAnnotation(methods, Test.class);

        //достаем методы с аннотацией @Before
        this.beforeMethods = getMethodsWithAnnotation(methods, Before.class);

        //достаем методы с аннотацией @After
        this.afterMethods = getMethodsWithAnnotation(methods, After.class);
    }

    public List<Method> getTestMethods() {
        return testMethods;
    }

    public void setTestMethods(List<Method> testMethods) {
        this.testMethods = testMethods;
    }

    public List<Method> getBeforeMethods() {
        return beforeMethods;
    }

    public void setBeforeMethods(List<Method> beforeMethods) {
        this.beforeMethods = beforeMethods;
    }

    public List<Method> getAfterMethods() {
        return afterMethods;
    }

    public void setAfterMethods(List<Method> afterMethods) {
        this.afterMethods = afterMethods;
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

    public <T> T instantiate(Object... args) {
        try {
            if (args.length == 0) {
                return (T) this.clazz.getDeclaredConstructor().newInstance();
            } else {
                Class<?>[] classes = toClasses(args);
                return (T) this.clazz.getDeclaredConstructor(classes).newInstance(args);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Class<?>[] toClasses(Object[] args) {
        return Arrays.stream(args).map(Object::getClass).toArray(Class<?>[]::new);
    }

    private List<Method> getMethodsWithAnnotation(Method[] methods, Class<? extends Annotation> annotationClazz) throws ClassNotFoundException {
        List<Method> listMethods = new ArrayList<>();
        //System.out.println("-----------достаем методы с аннотацией @Test");
        for (Method method : methods) {
            //System.out.println("-----------------------------------Метод:" + method.toString());
            if (method.isAnnotationPresent((Class<? extends Annotation>) Class.forName(annotationClazz.getCanonicalName()))) {
                method.setAccessible(true);
                listMethods.add(method);
            }
        }
        return listMethods;
    }

}
