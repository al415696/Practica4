package es.uji.tables;

import java.util.ArrayList;
import java.util.List;

public class Row {
    private ArrayList data = new ArrayList<Double>();

    public ArrayList<Double> getData() {
        return data;
    }

    public Row(List<Double> datos) {
        data = new ArrayList<Double>(datos);

    }
}