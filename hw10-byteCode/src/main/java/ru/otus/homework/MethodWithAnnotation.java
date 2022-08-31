package ru.otus.homework;

import java.lang.reflect.Method;

public class MethodWithAnnotation {
    private Method method;
    private Class<?>[] argsClasses;
    private Boolean isAnnotatedByLog;

    public MethodWithAnnotation() {
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

    public Boolean getAnnotatedByLog() {
        return isAnnotatedByLog;
    }

    public void setAnnotatedByLog(Boolean annotatedByLog) {
        isAnnotatedByLog = annotatedByLog;
    }
}
