package ru.otus.dataprocessor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

public class FileSerializer implements Serializer {
    private final Gson gson = new Gson();
    private final String fileName;


    public FileSerializer(String fileName) {
        this.fileName = fileName;
    }

    private static final Type REVIEW_TYPE = new TypeToken<TreeMap<String, Double>>() {
    }.getType();

    @Override
    public void serialize(Map<String, Double> data) {
        try (JsonWriter jsonWriter = new JsonWriter(new FileWriter(fileName))) {
            gson.toJson(data, REVIEW_TYPE, jsonWriter);
        } catch (Exception ex) {
            throw new FileProcessException(ex);
        }
    }

}
