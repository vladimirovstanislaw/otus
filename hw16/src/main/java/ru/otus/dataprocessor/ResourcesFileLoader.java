package ru.otus.dataprocessor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.otus.model.Measurement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ResourcesFileLoader implements Loader {
    private final Gson gson = new Gson();

    public ResourcesFileLoader(String fileName) {
    }

    @Override
    public List<Measurement> load() {
        //читает файл, парсит и возвращает результат
        List<Measurement> measurementList = new ArrayList<>();
        String pathToFile = "C:\\prj\\otus\\hw16\\src\\main\\resources\\inputData.json";
        String tempData = "";
        try (var bufferedReader = new BufferedReader(new FileReader(pathToFile))) {
            System.out.println("text from the file:");
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                tempData += line;
            }
        } catch (Exception ex) {

        }
        List<Measurement> k = gson.fromJson(tempData, new TypeToken<ArrayList<Measurement>>() {
        }.getType());


        return null;
    }

    public static void main(String[] args) {
        ResourcesFileLoader resourcesFileLoader = new
                ResourcesFileLoader("");
        resourcesFileLoader.load();
    }

}
