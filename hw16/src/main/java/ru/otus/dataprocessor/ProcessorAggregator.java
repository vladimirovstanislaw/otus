package ru.otus.dataprocessor;

import com.google.protobuf.MapEntry;
import ru.otus.model.Measurement;

import java.util.*;

public class ProcessorAggregator implements Processor {

    @Override
    public Map<String, Double> process(List<Measurement> data) {
        //группирует выходящий список по name, при этом суммирует поля value
        Map<String, Double> groupedData = new TreeMap<>();

        data.stream().forEach(measurement -> {

            if (groupedData.containsKey(measurement.getName())) {
                groupedData.put(measurement.getName(), groupedData.get(measurement.getName()) + measurement.getValue());
            } else {
                groupedData.put(measurement.getName(), measurement.getValue());
            }
        });
        return groupedData;
    }


}
