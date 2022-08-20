package ru.otus.homework;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.util.List;

public class TestSubsequence {
    private  Method testMethod;
    private List<Method> beforeMethods;
    private  List<Method> afeterMethods;

    public TestSubsequence() {
    }

    public TestSubsequence(Method testMethod, List<Method> beforeMethods, List<Method> afeterMethods) {
        this.testMethod = testMethod;
        this.beforeMethods = beforeMethods;
        this.afeterMethods = afeterMethods;
    }

    public Method getTestMethod() {
        return testMethod;
    }

    public void setTestMethod(Method testMethod) {
        this.testMethod = testMethod;
    }

    public List<Method> getBeforeMethods() {
        return beforeMethods;
    }

    public void setBeforeMethods(List<Method> beforeMethods) {
        this.beforeMethods = beforeMethods;
    }

    public List<Method> getAfeterMethods() {
        return afeterMethods;
    }

    public void setAfeterMethods(List<Method> afeterMethods) {
        this.afeterMethods = afeterMethods;
    }

}
