package ru.otus.classloader;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.io.IOException;
import java.util.Arrays;

public class MyClassLoaderDemo {
    public static void main(String[] args) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, URISyntaxException {
        new MyClassLoaderDemo().start();
    }

    private void start() throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, URISyntaxException {
        var loader = new MyClassLoader();
        Class<?> clazz = loader.defineClass("ru.otus.classloader.ClassForLoading");
        System.out.println("methods:");
        Arrays.stream(clazz.getMethods())
                .forEach(method -> System.out.println(method.getName()));
        Constructor<?> constructor = clazz.getConstructor();
        Object object = constructor.newInstance();
        System.out.println("------");

        clazz.getMethod("action").invoke(object);
    }

    private static class MyClassLoader extends ClassLoader {
        Class<?> defineClass(String className) throws IOException, URISyntaxException {

            var file = new File(getFileName(className));
            byte[] byteCode = Files.readAllBytes(file.toPath());
            return super.defineClass(className, byteCode, 0, byteCode.length);
        }

        private String getFileName(String className) throws URISyntaxException {
            String clazzName = className.substring(className.lastIndexOf('.') + 1) + ".class";
            String dir = System.getProperty("user.dir") + "\\hw10-byteCode\\" + "myClass\\";
            return dir + clazzName;
        }
    }
}
