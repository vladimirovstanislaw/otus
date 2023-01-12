package ru.otus.protobuf.hw.service;

import java.util.List;

public interface ServerService {
    List<Integer> getNumberSequence(int firstValue, int lastValue);
}
