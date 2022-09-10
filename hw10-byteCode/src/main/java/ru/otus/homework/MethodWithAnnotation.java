package ru.otus.homework;

import java.lang.reflect.Method;
import java.util.Arrays;

public class MethodWithAnnotation {
    private Method method;
    private Class<?>[] argsClasses;
    private String[] argsClassesString;

    public MethodWithAnnotation() {
    }

    public MethodWithAnnotation(Method method, Class<?>[] argsClasses, String[] argsClassesString) {
        this.method = method;
        this.argsClasses = argsClasses;
        this.argsClassesString = argsClassesString;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Class<?>[] getArgsClasses() {
        return argsClasses;
    }

    public void setArgsClasses(Class<?>[] argsClasses) {
        this.argsClasses = argsClasses;
    }

    public String[] getArgsClassesString() {
        return argsClassesString;
    }

    public void setArgsClassesString(String[] argsClassesString) {
        this.argsClassesString = argsClassesString;
    }

    @Override
    public String toString() {
        return "MethodWithAnnotation{" +
                "method=" + method +
                ", argsClasses=" + Arrays.toString(argsClasses) +
                ", argsClassesString=" + Arrays.toString(argsClassesString) +
                '}';
    }
}
