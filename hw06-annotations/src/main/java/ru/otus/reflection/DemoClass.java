package ru.otus.reflection;

import java.util.Objects;

public class DemoClass implements DemoClassInt{

    public int publicFieldForDemo;

    private String valuePrivate = "initValue";

    public DemoClass(String valuePrivate) {
        this.valuePrivate = valuePrivate;
    }

    @SimpleAnnotation
    public String getValuePrivate() {
        return valuePrivate;
    }

    public void setValuePrivate(String valuePrivate) {
        this.valuePrivate = valuePrivate;
    }


    private void privateMethod() {
        System.out.println("privateMethod executed");
    }


    @SimpleAnnotation
    @Override
    public String toString() {
        return "DemoClass{" +
                "publicFieldForDemo=" + publicFieldForDemo +
                ", value='" + valuePrivate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DemoClass demoClass = (DemoClass) o;
        return publicFieldForDemo == demoClass.publicFieldForDemo && Objects.equals(valuePrivate, demoClass.valuePrivate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publicFieldForDemo, valuePrivate);
    }

}
