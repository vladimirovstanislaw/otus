package ru.otus.model;

public class ObjectAndMethod {
    private final Object object;
    private final String interfaceName;
    private final MethodWithAnnotation methodWithAnnotation;

    public ObjectAndMethod(Object object, String interfaceName, MethodWithAnnotation methodWithAnnotation) {
        this.object = object;
        this.interfaceName = interfaceName;
        this.methodWithAnnotation = methodWithAnnotation;
    }

    public Object getObject() {
        return object;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public MethodWithAnnotation getMethodWithAnnotation() {
        return methodWithAnnotation;
    }
}
