package ru.otus.services.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.api.SensorDataProcessor;
import ru.otus.api.model.SensorData;
import ru.otus.lib.SensorDataBufferedWriter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;


public class SensorDataProcessorBuffered implements SensorDataProcessor {
    private static final Logger log = LoggerFactory.getLogger(SensorDataProcessorBuffered.class);
    private final BlockingQueue<SensorData> sensorDataQueue;
    private final int bufferSize;
    private final SensorDataBufferedWriter writer;

    public SensorDataProcessorBuffered(int bufferSize, SensorDataBufferedWriter writer) {
        this.bufferSize = bufferSize;
        this.writer = writer;
        this.sensorDataQueue = new PriorityBlockingQueue<>(bufferSize, Comparator.comparing(SensorData::getMeasurementTime));
    }

    @Override
    public void process(SensorData data) {
        if (sensorDataQueue.size() >= bufferSize) {
            flush();
            sensorDataQueue.offer(data);
        } else {
            sensorDataQueue.offer(data);
        }
    }

    public void flush() {
        try {
            if (sensorDataQueue.size() == 0) {

            } else {
                List<SensorData> sensorDataList = new ArrayList<>();
                sensorDataQueue.drainTo(sensorDataList);
                writer.writeBufferedData(sensorDataList);

                //sensorDataQueue.clear();
            }
        } catch (Exception e) {
            log.error("Ошибка в процессе записи буфера", e);
        }
    }

    @Override
    public void onProcessingEnd() {
        flush();
    }
}
