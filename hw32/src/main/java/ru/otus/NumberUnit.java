package ru.otus;

import java.util.ArrayList;
import java.util.List;

public class NumberUnit {
    private int number;
    private int countOfThreads;
    private List<Boolean> wasShowedByThread;


    public NumberUnit(int number, int countOfThreads) {
        this.number = number;
        this.countOfThreads = countOfThreads;
        wasShowedByThread = new ArrayList<>();
        for (int i = 0; i < countOfThreads; i++) {
            wasShowedByThread.add(false);
        }
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCountOfThreads() {
        return countOfThreads;
    }

    public void setCountOfThreads(int countOfThreads) {
        this.countOfThreads = countOfThreads;
    }

    public void nullifyWasShowedByThread() {
        for (int i = 0; i < countOfThreads; i++) {
            wasShowedByThread.set(i, false);
        }
    }
}
