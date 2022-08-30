package ru.otus.aop.instrumentation.proxy;


/*
    java -javaagent:proxyDemo.jar -jar proxyDemo.jar
    java -javaagent:"C:\Projects\otus\hw10-byteCode\build\libs\proxyDemo.jar" -jar "C:\Projects\otus\hw10-byteCode\build\libs\proxyDemo.jar"
*/
public final class ProxyDemo {

    public static void main(String[] args) {
        var myClass = new MyClassImpl();
        myClass.secureAccess("Security Param");
    }
}
