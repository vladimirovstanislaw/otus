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
import java.util.concurrent.PriorityBlockingQueue;


public class SensorDataProcessorBuffered implements SensorDataProcessor {
    private static final Logger log = LoggerFactory.getLogger(SensorDataProcessorBuffered.class);
    private final Queue<SensorData> sensorDataQueue;
    private final int bufferSize;
    private final SensorDataBufferedWriter writer;

    public SensorDataProcessorBuffered(int bufferSize, SensorDataBufferedWriter writer) {
        this.bufferSize = bufferSize;
        this.writer = writer;
        this.sensorDataQueue = new PriorityBlockingQueue<>(bufferSize, Comparator.comparing(SensorData::getMeasurementTime));
    }

    @Override
    public synchronized void process(SensorData data) {
        if (sensorDataQueue.size() >= bufferSize) {
            flush();
            sensorDataQueue.offer(data);
        } else {
            sensorDataQueue.offer(data);
        }
    }

    public synchronized void flush() {
        try {
            if (sensorDataQueue.size() == 0) {

            } else {
                List<SensorData> sensorDataList = new ArrayList<>();
                int sensorDataQueueSize = sensorDataQueue.size();
                for (int i = 0; i < sensorDataQueueSize; i++) {
                    sensorDataList.add(sensorDataQueue.poll());
                }
                writer.writeBufferedData(sensorDataList);

                sensorDataQueue.clear();
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
