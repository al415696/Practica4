package es.uji.tables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TableWithLabels extends Table {

    private Map<String, Integer> labelsToIndex = new HashMap<String, Integer>();

    private int nextIndex = -1;

    public void addRow(Row filaConLabel, String label) {
        if (getIndexFromLabel(label) == null)
            labelsToIndex.put(label, ++nextIndex);
        super.addRow(filaConLabel);
    }

    public void addRow(ArrayList<Double> fila, String label) {
        RowWithLabel addedRow;
        if (getIndexFromLabel(label) == null) {
            labelsToIndex.put(label, ++nextIndex);
            addedRow = new RowWithLabel(fila, nextIndex);
        } else addedRow = new RowWithLabel(fila, getIndexFromLabel(label));

        super.addRow(addedRow);
    }

    @Override
    public RowWithLabel getRowAt(int pos) {
        return (RowWithLabel) super.getRowAt(pos);
    }


    public Integer getIndexFromLabel(String label) {

        return labelsToIndex.get(label);
    }

    @Override
    public int numberColumns() {
        return super.numberColumns() - 1;
    }

    public int getNumOfLabels() {
        return nextIndex + 1;
    }

}

