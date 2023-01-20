package ru.otus.protobuf.hw.service;

import java.util.ArrayList;
import java.util.List;

public class ServerServiceImpl implements ServerService {
    @Override
    public List<Integer> getNumberSequence(int firstValue, int lastValue) {
        List<Integer> integerList = new ArrayList<>();
        if (firstValue >= lastValue) {
            throw new RuntimeException("Unexpected values");
        }
        for (int i = firstValue; i <= lastValue; i++) {
            integerList.add(i);
        }
        return integerList;
    }
}
