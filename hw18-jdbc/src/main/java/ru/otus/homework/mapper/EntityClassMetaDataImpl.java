package ru.otus.homework.mapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {
    private Class<T> type;
    private Constructor constructor;
    private List<Field> allFields;

    private Field fieldId;

    private List<Field> allNonIdFields;


    public EntityClassMetaDataImpl(Class<T> type) throws NoSuchMethodException {
        this.type = type;
        this.constructor = type.getConstructor();
        this.allFields = (List<Field>) List.of(type.getDeclaredFields());
        this.allNonIdFields = new ArrayList<>();
        allFields.stream().forEach(field -> {
            if (field.isAnnotationPresent(Id.class)) {
                this.fieldId = field;
            } else {
                allNonIdFields.add(field);
            }
        });
    }

    @Override
    public String getName() {
        return type.getSimpleName();
    }

    @Override
    public Constructor getConstructor() {
        return constructor;
    }

    @Override
    public Field getIdField() {
        return fieldId;
    }

    @Override
    public List<Field> getAllFields() {
        return allFields;
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return allNonIdFields;
    }
}
