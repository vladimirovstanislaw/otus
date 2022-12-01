package ru.otus.appcontainer;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;
import ru.otus.model.MethodWithAnnotation;
import ru.otus.model.ObjectAndMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();
    private final List<ObjectAndMethod> objectsWithMethods = new ArrayList<>();
    private static final Logger log = LoggerFactory.getLogger(AppComponentsContainerImpl.class);


    public AppComponentsContainerImpl(Class<?> initialConfigClass) throws Exception {


        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) throws Exception {
        checkConfigClass(configClass);

        Method[] methods = configClass.getMethods();
        List<Method> appComponentMethods = getMethodsWithAnnotation(methods, AppComponent.class);

        //Лист для хранения аннотированных методов с мета-инфомацией
        List<MethodWithAnnotation> methodWithAnnotationStringList = new ArrayList<>();


        for (Method met : appComponentMethods) {
            AppComponent annotation = met.getDeclaredAnnotation(AppComponent.class);
            MethodWithAnnotation methodWithAnnotation = new MethodWithAnnotation(met, annotation, annotation.order(), annotation.name(), met.getReturnType());
            methodWithAnnotationStringList.add(methodWithAnnotation);
        }
        //сортируем коллекцию по order
        Collections.sort(methodWithAnnotationStringList, Comparator.comparingInt(MethodWithAnnotation::getOrder));
        //создаем объект config класса
        Constructor<?> confClassConstructor = configClass.getConstructor();
        Object object = confClassConstructor.newInstance();
        //Заполняем объекты контекста
        for (MethodWithAnnotation methodWithAnnotation : methodWithAnnotationStringList) {
            int parameterCount = methodWithAnnotation.getMethod().getParameterCount();
            log.info("---\nName: {}, order:{}, parameterCount: {}", methodWithAnnotation.getName(), methodWithAnnotation.getOrder(), parameterCount);
            if (parameterCount == 0) {
                Object resultOfMethodInvokation = methodWithAnnotation.getMethod().invoke(object);
                //добавляем компонент в наши коллекции
                putObjectInContext(resultOfMethodInvokation, methodWithAnnotation);
            } else {
                List<Object> parameters = new ArrayList<>();

                for (var o : methodWithAnnotation.getMethod().getParameterTypes()) {
                    for (var objectAndMethod : objectsWithMethods) {
                        if (objectAndMethod.getInterfaceName().equals(o.toString())) {
                            parameters.add(objectAndMethod.getObject());
                        }
                    }

                }
                Object resultOfMethodInvokation = methodWithAnnotation.getMethod().invoke(object, parameters.toArray());
                //добавляем компонент в наши коллекции
                putObjectInContext(resultOfMethodInvokation, methodWithAnnotation);


            }
        }
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        List<C> listOfObjects = new ArrayList<>();

        for (ObjectAndMethod objectAndMethod : objectsWithMethods) {
            if (componentClass.equals(objectAndMethod.getObject().getClass()) || componentClass.toString().equals(objectAndMethod.getInterfaceName())) {
                listOfObjects.add((C) objectAndMethod.getObject());
            }
        }
        if (listOfObjects.size() == 0) {
            throw new RuntimeException("We don't have that object");
        }
        if (listOfObjects.size() > 1) {
            throw new RuntimeException("We have more than 1 objects of that type");
        }
        return listOfObjects.get(0);
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        return (C) appComponentsByName.get(componentName);
    }

    private List<Method> getMethodsWithAnnotation(Method[] methods, Class<? extends Annotation> annotationClazz) throws ClassNotFoundException {
        List<Method> listMethods = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent((Class<? extends Annotation>) Class.forName(annotationClazz.getCanonicalName()))) {
                method.setAccessible(true);
                listMethods.add(method);
            }
        }
        return listMethods;
    }

    private void putObjectInContext(Object object, MethodWithAnnotation methodWithAnnotation) {
        //check for duplicate
        var historicalObject = appComponentsByName.get(methodWithAnnotation.getName());
        if (historicalObject != null) {
            throw new RuntimeException("We already have object with that name in context");
        }
        //добавляем объект в контекст
        appComponents.add(object);
        appComponentsByName.put(methodWithAnnotation.getName(), object);
        objectsWithMethods.add(new ObjectAndMethod(object, methodWithAnnotation.getReturnTypeOfMethod().toString(), methodWithAnnotation));
    }

}
