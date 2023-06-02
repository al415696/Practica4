package es.uji.csv;

import es.uji.tables.Row;
import es.uji.tables.RowWithLabel;
import es.uji.tables.Table;
import es.uji.tables.TableWithLabels;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CSV {

    public Table readTable(String direc) throws FileNotFoundException {
        String[] datos;
        Table nuevaTabla = new Table();
        ArrayList<String> datosHeader = new ArrayList<>();

        Scanner entrada = new Scanner(new File(direc));

        checkForHeader(entrada, datosHeader,nuevaTabla);

        nuevaTabla.addHeader(datosHeader);

        while (entrada.hasNextLine()) {
            ArrayList<Double> datosLinea = new ArrayList<>();
            datos = entrada.nextLine().split(",");
            for (String dato : datos) {
                datosLinea.add(Double.parseDouble(dato));
            }
            nuevaTabla.addRow(new Row(datosLinea));
        }
        return nuevaTabla;
    }

    public TableWithLabels readTableWithLabels(String direc) throws FileNotFoundException {
        String[] datos;
        TableWithLabels nuevaTabla = new TableWithLabels();
        ArrayList<String> datosHeader = new ArrayList<>();
        Scanner entrada = new Scanner(new File(direc));
        checkForHeader(entrada, datosHeader,nuevaTabla);
        int nextRowIndex;
        while (entrada.hasNextLine()) {
            ArrayList<Double> datosLinea = new ArrayList<>();

            datos = entrada.nextLine().split(",");
            for (int i = 0; i < datos.length - 1; i++) {
                datosLinea.add(Double.parseDouble(datos[i]));
            }
            if (nuevaTabla.getIndexFromLabel(datos[datos.length - 1]) == null)
                nextRowIndex = nuevaTabla.getNumOfLabels();
            else
                nextRowIndex = nuevaTabla.getIndexFromLabel(datos[datos.length - 1]);

            nuevaTabla.addRow(new RowWithLabel(datosLinea, nextRowIndex), datos[datos.length - 1]);
        }
        return nuevaTabla;
    }

    private void checkForHeader(Scanner entrada, ArrayList<String> datosHeader, Table nuevaTabla){
        if (entrada.hasNextLine()) {
            String[] datos = entrada.nextLine().split(",");
            Collections.addAll(datosHeader, datos);
            nuevaTabla.addHeader(datosHeader);
        }
    }
}
