package ru.otus.homework;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;


public class ProxyIoc {
    private ProxyIoc() {
    }

    static TestLoggingInterface createLogging() {
        InvocationHandler handler = new DemoInvocationHandler(new TestLogging());
        return (TestLoggingInterface) Proxy.newProxyInstance(ProxyIoc.class.getClassLoader(), new Class<?>[]{TestLoggingInterface.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface testLoggingClass;

        DemoInvocationHandler(TestLoggingInterface testLoggingClass) {
            this.testLoggingClass = testLoggingClass;
        }


        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
            if (args == null) {
                var hasAnnotation = this.testLoggingClass.getClass().getMethod(method.getName()).isAnnotationPresent(Log.class);
                if (hasAnnotation == true) {
                    System.out.print("executed method: " + method.getName() + ", param: null");
                    System.out.println();
                }
                return method.invoke(testLoggingClass);
            } else {
                var hasAnnotation = this.testLoggingClass.getClass().getMethod(method.getName(), toClasses(args)).isAnnotationPresent(Log.class);
                if (hasAnnotation == true) {
                    System.out.print("executed method: " + method.getName() + ", param:");
                    int[] array = (int[]) args[0];
                    for (int x : array) {
                        System.out.print(" " + x);
                    }
                    System.out.println();
                }
                return method.invoke(testLoggingClass, args);
            }

        }

        private static Class<?>[] toClasses(Object[] args) {
            return Arrays.stream(args).map(Object::getClass).toArray(Class<?>[]::new);
        }

    }

}
