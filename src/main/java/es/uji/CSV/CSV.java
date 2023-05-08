package es.uji.CSV;

import es.uji.Rows.Row;
import es.uji.Rows.RowWithLabel;
import es.uji.Tables.Table;
import es.uji.Tables.TableWithLabels;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSV {

    public Table readTable(String direc) throws FileNotFoundException {
        String[] datos;
        Table nuevaTabla = new Table();
        ArrayList<String> datosHeader = new ArrayList<>();

        Scanner entrada = new Scanner(new File(direc));
        if (entrada.hasNextLine()) {
            datos = entrada.nextLine().split(",");
            for (int i = 0; i < datos.length; i++) {
                datosHeader.add(datos[i]);
            }

            nuevaTabla.addHeader(datosHeader);
        }

        nuevaTabla.addHeader(datosHeader);

        while (entrada.hasNextLine()) {
            ArrayList<Double> datosLinea = new ArrayList<>();

            datos = entrada.nextLine().split(",");
            for (int i = 0; i < datos.length; i++) {
                datosLinea.add(Double.parseDouble(datos[i]));
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
        if (entrada.hasNextLine()) {
            datos = entrada.nextLine().split(",");
            for (int i = 0; i < datos.length; i++) {
                datosHeader.add(datos[i]);
            }
            nuevaTabla.addHeader(datosHeader);
        }
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

}
