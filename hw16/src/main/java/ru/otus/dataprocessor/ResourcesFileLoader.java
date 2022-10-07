package ru.otus.dataprocessor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import ru.otus.model.Measurement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ResourcesFileLoader implements Loader {
    private final Gson gson = new Gson();
    private static final Type REVIEW_TYPE = new TypeToken<ArrayList<Measurement>>() {
    }.getType();
    private final String fileName;


    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() {
        try (JsonReader reader = new JsonReader(new FileReader(ResourcesFileLoader.class.getClassLoader().getResource(fileName).getFile()))) {
            List<Measurement> data = gson.fromJson(reader, REVIEW_TYPE);
            return data;
        } catch (Exception ex) {
            throw new FileProcessException(ex);
        }

    }
}
