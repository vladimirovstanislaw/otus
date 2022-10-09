package ru.otus.homework.mapper;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Создает SQL - запросы
 */
public interface EntitySQLMetaData {
    String getSelectAllSql();

    String getSelectByIdSql();

    String getInsertSql();

    String getUpdateSql();
    <T> List<Object> paramsOfObject(T t) throws IllegalAccessException;

    <T> T createAndFillObject(ResultSet resultSet) throws InvocationTargetException, InstantiationException, IllegalAccessException, SQLException;
}
