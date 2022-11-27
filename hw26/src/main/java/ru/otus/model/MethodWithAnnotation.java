package ru.otus.model;

import ru.otus.appcontainer.api.AppComponent;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class MethodWithAnnotation {
    private final Method method;
    private final AppComponent annotation;
    private final int order;
    private final String name;
    private final Class<?> returnTypeOfMethod;

    public MethodWithAnnotation(Method method, AppComponent annotation, int order, String name, Class<?> returnTypeOfMethod) {
        this.method = method;
        this.annotation = annotation;
        this.order = order;
        this.name = name;
        this.returnTypeOfMethod = returnTypeOfMethod;
    }

    public Method getMethod() {
        return method;
    }

    public AppComponent getAnnotation() {
        return annotation;
    }

    public int getOrder() {
        return order;
    }

    public String getName() {
        return name;
    }

    public Class<?> getReturnTypeOfMethod() {
        return returnTypeOfMethod;
    }
}
