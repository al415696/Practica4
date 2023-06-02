package es.uji.tables;

import java.util.List;

public class RowWithLabel extends Row {
    private int numberClass;

    public int getNumberClass() {
        return numberClass;
    }

    public RowWithLabel(List<Double> datos, int numberC) {
        super(datos);
        numberClass = numberC;
    }
}
