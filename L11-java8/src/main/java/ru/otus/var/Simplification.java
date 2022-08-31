package ru.otus.var;

import java.io.*;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Simplification {

    String func() throws IOException {
        StringBuilder output = new StringBuilder();
        try (InputStream inputStream = new FileInputStream("lines.txt");
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, UTF_8)) {

            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
            return output.toString();
        }
    }

    // Типы данных переменных очевидны, поэтому можно использовать var
    String funcVar() throws IOException {
        var output = new StringBuilder();
        try (var inputStreamReader = new InputStreamReader(
                Simplification.class.getResourceAsStream("/lines.txt"), UTF_8)) {

            var reader = new BufferedReader(inputStreamReader);
            var line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
            return output.toString();
        }
    }


    //Пример серьезнее

    boolean find(Map<? extends String, ? extends Integer> mapA,
                 Map<? extends String, ? extends Integer> mapB,
                 int value) {
        String keyA = null;
        for (Map.Entry<? extends String, ? extends Integer> entry : mapA.entrySet()) {
            if (entry.getValue().equals(value)) {
                keyA = entry.getKey();
                break;
            }
        }

        if (keyA == null) {
            return false;
        }

        String keyB = null;
        int stepCount = 0;
        for (Map.Entry<? extends String, ? extends Integer> entry : mapB.entrySet()) {
            if (entry.getValue().equals(value)) {
                keyB = entry.getKey();
                stepCount++;
                break;
            }
        }
        System.out.println(stepCount);
        return keyA.equals(keyB);
    }

    //применяем var
    boolean findVar(Map<? extends String, ? extends Integer> mapA,
                    Map<? extends String, ? extends Integer> mapB,
                    int value) {
        String keyA = null;
        for (var entry : mapA.entrySet()) {
            if (entry.getValue().equals(value)) {
                keyA = entry.getKey();
                break;
            }
        }

        if (keyA == null) {
            return false;
        }

        String keyB = null;
        int stepCount = 0;
        for (var entry : mapB.entrySet()) {
            if (entry.getValue().equals(value)) {
                keyB = entry.getKey();
                stepCount++;
                break;
            }
        }
        System.out.println(stepCount);
        return keyA.equals(keyB);
    }

    //пример, в котором var не подойдет
    void funcNotGood() {
        var val = getSomething(); // не понятно, что тут возврашается
        System.out.println(val);
    }

    //может быть где-то далеко, в другом классе
    private String getSomething() {
        return "";
    }
}
