package ru.otus.homework;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;


public class ProxyIoc {
    private ProxyIoc() {
    }

    static TestLoggingInterface createLogging() {
        InvocationHandler handler = new DemoInvocationHandler(new TestLogging());
        return (TestLoggingInterface) Proxy.newProxyInstance(ProxyIoc.class.getClassLoader(), new Class<?>[]{TestLoggingInterface.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface testLoggingClass;
        HashMap<String, MethodWithAnnotation> hashMap = new HashMap<>();

        DemoInvocationHandler(TestLoggingInterface testLoggingClass) {
            this.testLoggingClass = testLoggingClass;
        }


        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
            System.out.println("====================================================================================================");
            if (hashMap.size() == 0) {
                var methods = this.testLoggingClass.getClass().getMethods();
                for (var publicMethod : methods) {
                    System.out.println("this.method: " + publicMethod);
                    System.out.println("this.method.hashCode(): " + publicMethod.hashCode());
                    System.out.println("this.method.getName(): " + publicMethod.getName());
                    if (publicMethod.getDeclaredAnnotations().length > 0) {

                        if (this.testLoggingClass.getClass().getMethod(method.getName(), toClasses(args)).isAnnotationPresent(Log.class) == true) {
                            MethodWithAnnotation methodWithAnnotation = new MethodWithAnnotation();
                            methodWithAnnotation.setMethod(publicMethod);
                            methodWithAnnotation.setAnnotatedByLog(true);
                            methodWithAnnotation.setArgsClasses(toClasses(publicMethod.getParameterTypes()));
                            hashMap.put(publicMethod.getName(), methodWithAnnotation);
                        }
                    }
                }
            }

            System.out.println("00000000000000000Proxy00000000000000000");
            System.out.println("method:" + method);
            System.out.println("method.hashCode(): " + method.hashCode());
            System.out.println("method.getName(): " + method.getName());
            System.out.println("args" + args);
            var tryToMakeItFaster = hashMap.get(method.getName());
            if (tryToMakeItFaster != null) {


                System.out.println("hhi butch");
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
            } else {
                return method.invoke(testLoggingClass, args);
            }

        }

    }

    private static Class<?>[] toClasses(Object[] args) {
//            if ()
//                Class<?>[] returnable = new Class<?>[args.length];
//
//            for (var arg : args) {
//                returnable.
//            }
        return Arrays.stream(args).map(Object::getClass).toArray(Class<?>[]::new);
    }

}


