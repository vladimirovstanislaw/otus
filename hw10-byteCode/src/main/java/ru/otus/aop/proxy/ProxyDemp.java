package ru.otus.aop.proxy;

public class ProxyDemp {
    public static void main(String[] args) {
        MyClassInterface myClass = Ioc.createMyClass();
        myClass.secureAccess("Security Param");
    }
}
