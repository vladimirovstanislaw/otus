package ru.otus.model;

import java.util.ArrayList;
import java.util.List;

public class ListOfMeasurement {
    List<Measurement> list;

    public ListOfMeasurement() {
    }

    public ListOfMeasurement(List<Measurement> list) {
        this.list = list;
    }

    public List<Measurement> getList() {
        return list;
    }

    public void setList(List<Measurement> list) {
        this.list = list;
    }
}
