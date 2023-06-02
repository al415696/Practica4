package es.uji.tables;

import java.util.ArrayList;

public class Table {
    ArrayList header = new ArrayList<String>();
    private ArrayList<Row> rows = new ArrayList<Row>();

    public void addRow(Row fila) {
        rows.add(fila);
    }

    public void addHeader(ArrayList<String> campos) {
        header = campos;
    }

    public Row getRowAt(int pos) {
        return rows.get(pos);
    }

    public ArrayList<String> getHeader() {
        return header;
    }

    public int numberColumns() {
        return header.size();
    }

    public Integer getSize() {
        return rows.size();
    }
}