package ru.otus.homework.mapper;

import ru.otus.crm.model.Client;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntitySQLMetaDataImpl<T> implements EntitySQLMetaData {
    private EntityClassMetaData entityClassMetaData;
    private final String selectAll;
    private final String selectById;
    private final String insert;
    private final String update;

    public EntitySQLMetaDataImpl(EntityClassMetaData entityClassMetaData) {
        this.entityClassMetaData = entityClassMetaData;
        this.selectAll = "select * from " + this.entityClassMetaData.getName();
        this.selectById = "select * from " + this.entityClassMetaData.getName() + " where " + this.entityClassMetaData.getIdField().getName() + "  = ?";

        StringBuilder insertBuilder = new StringBuilder();
        insertBuilder.append("insert into ").append(this.entityClassMetaData.getName()).append("(");
        for (int i = 0; i < this.entityClassMetaData.getFieldsWithoutId().size(); i++) {
            insertBuilder.append(((Field) this.entityClassMetaData.getFieldsWithoutId().get(i)).getName());
            if (!(i == (this.entityClassMetaData.getFieldsWithoutId().size() - 1))) {
                insertBuilder.append(",");
            }
        }
        insertBuilder.append(") values (");
        for (int i = 0; i < this.entityClassMetaData.getFieldsWithoutId().size(); i++) {
            insertBuilder.append("?");
            if (!(i == (this.entityClassMetaData.getFieldsWithoutId().size() - 1))) {
                insertBuilder.append(",");
            }
        }
        insert = insertBuilder.append(")").toString();

        StringBuilder updateBuilder = new StringBuilder();
        updateBuilder.append("update ").append(this.entityClassMetaData.getName()).append(" set ");
        for (int i = 0; i < this.entityClassMetaData.getFieldsWithoutId().size(); i++) {
            updateBuilder.append(((Field) this.entityClassMetaData.getFieldsWithoutId().get(i)).getName()).append(" = ?");
            if (!(i == (this.entityClassMetaData.getFieldsWithoutId().size() - 1))) {
                updateBuilder.append(",");
            }
        }
        updateBuilder.append(" where ").append(entityClassMetaData.getIdField().getName()).append(" = ?");
        this.update = updateBuilder.toString();


    }

    @Override
    public String getSelectAllSql() {
        return selectAll;
    }

    @Override
    public String getSelectByIdSql() {
        return selectById;
    }

    @Override
    public String getInsertSql() {
        return insert;
    }

    @Override
    public String getUpdateSql() {
        return update;
    }

    @Override
    public <T> List<Object> paramsOfObject(T t) throws IllegalAccessException {
        List<Object> params = new ArrayList<>();
        for (var field : entityClassMetaData.getAllFields()) {
            var reallyField = (Field) field;// странно, что в getAllFields() явно указано, что возвращаем List<Field>, однако он компилятор утвержадает, что в листе Object-ы
            reallyField.setAccessible(true);
            var param = reallyField.get(t);
            if (field == entityClassMetaData.getIdField()) {
                continue;
            }
            if (param == null) {
                params.add("nullParam");
            } else {
                params.add(param);
            }

        }
        return params;
    }

    @Override
    public <T> T createAndFillObject(ResultSet resultSet) throws InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        Constructor<T> typeConstructor = entityClassMetaData.getConstructor();
        var object = typeConstructor.newInstance();
        for (var field : entityClassMetaData.getAllFields()) {
            Field realField = (Field) field;
            realField.setAccessible(true);
            var fieldType = realField.getType().toString();
            switch (fieldType) {
                case "class java.lang.Long": {
                    realField.set(object, resultSet.getLong(realField.getName()));
                    break;
                }
                case "class java.lang.String": {
                    realField.set(object, resultSet.getString(realField.getName()));
                    break;
                }
                default: {
                    throw new UnsupportedOperationException();
                }
            }


        }
        return object;
    }


}
