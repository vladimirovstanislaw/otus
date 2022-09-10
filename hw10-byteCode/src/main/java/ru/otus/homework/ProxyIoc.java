package ru.otus.homework;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class ProxyIoc {
    private ProxyIoc() {
    }

    static TestLoggingInterface createLogging() {
        InvocationHandler handler = new DemoInvocationHandler(new TestLogging());
        return (TestLoggingInterface) Proxy.newProxyInstance(ProxyIoc.class.getClassLoader(), new Class<?>[]{TestLoggingInterface.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private static int count;
        private final TestLoggingInterface testLoggingClass;
        private Set<MethodWithAnnotation> methodSet = new HashSet<>();

        DemoInvocationHandler(TestLoggingInterface testLoggingClass) {
            this.testLoggingClass = testLoggingClass;
            count = 0;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
            if (count == 0) {
                count++;
                var methods = this.testLoggingClass.getClass().getMethods();
                for (var publicMethod : methods) {
                    if (publicMethod.getDeclaredAnnotations().length > 0) {
                        if (publicMethod.getParameterTypes().length > 0) {
                            if (this.testLoggingClass.getClass().getMethod(publicMethod.getName(), toClasses(args)).isAnnotationPresent(Log.class) == true) {
                                MethodWithAnnotation methodWithAnnotation = new MethodWithAnnotation();
                                methodWithAnnotation.setMethod(publicMethod);
                                methodWithAnnotation.setArgsClasses(toClasses(publicMethod.getParameterTypes()));
                                methodWithAnnotation.setArgsClassesString(toClassesString(publicMethod.getParameterTypes()));
                                methodSet.add(methodWithAnnotation);
                            }
                        } else {
                            if (this.testLoggingClass.getClass().getMethod(publicMethod.getName()).isAnnotationPresent(Log.class) == true) {
                                MethodWithAnnotation methodWithAnnotation = new MethodWithAnnotation();
                                methodWithAnnotation.setMethod(publicMethod);
                                methodWithAnnotation.setArgsClasses(toClasses(publicMethod.getParameterTypes()));
                                methodWithAnnotation.setArgsClassesString(toClassesString(publicMethod.getParameterTypes()));
                                methodSet.add(methodWithAnnotation);
                            }

                        }
                    }
                }
            }
            MethodWithAnnotation tryToMakeItFaster = null;

            for (var loggedMethod : methodSet) {
                if (loggedMethod.getMethod().getName().equals(method.getName())) {
                    if (Arrays.equals(loggedMethod.getArgsClassesString(), toClassesString(toClasses(args)))) {
                        tryToMakeItFaster = loggedMethod;
                    }
                }
            }


            if (tryToMakeItFaster != null) {

                if (args == null || args.length == 0) {
                    System.out.print("executed method: " + method.getName() + ", param: null");
                    System.out.println();
                    return method.invoke(testLoggingClass);
                } else {

                    System.out.print("executed method: " + method.getName() + ", param:");

                    int[] array = (int[]) args[0];
                    for (int x : array) {
                        System.out.print(" " + x);
                    }
                    System.out.println();

                    return method.invoke(testLoggingClass, args);
                }
            } else {
                return method.invoke(testLoggingClass, args);
            }

        }

    }

    private static Class<?>[] toClasses(Object[] args) {
        if (args == null) {
            return null;
        } else {
            return Arrays.stream(args).map(Object::getClass).toArray(Class<?>[]::new);
        }
    }

    private static String[] toClassesString(Class<?>[] args) {
        if (args != null && args.length > 0) {
            String[] classesOfArgs = new String[args.length];
            for (int i = 0; i < args.length; i++) {
                if (args[i].getName().equals("int")) {
                    classesOfArgs[i] = "java.lang.Integer";
                } else {
                    classesOfArgs[i] = args[i].getName();
                }
            }
            Arrays.sort(classesOfArgs);
            return classesOfArgs;
        } else {
            return null;
        }
    }

}


