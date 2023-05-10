package es.uji.csv;

import es.uji.rows.RowWithLabel;
import es.uji.tables.TableWithLabels;

import java.util.ArrayList;

public class CSVLabeledFileReader extends CSVUnlabeledFileReader{


    public CSVLabeledFileReader(String source){
        super(source);
        tableActual = new TableWithLabels();
    }
    @Override
    protected void processData(String data) {
        String[] datos;
        ArrayList listDatos = new ArrayList();
        datos = data.split(",");
        int nextRowIndex;
        for (int i = 0; i < datos.length - 1; i++) {
            listDatos.add(Double.parseDouble(datos[i]));
        }
        if (((TableWithLabels)tableActual).getIndexFromLabel(datos[datos.length - 1]) == null)
            nextRowIndex = ((TableWithLabels)tableActual).getNumOfLabels();
        else
            nextRowIndex = ((TableWithLabels)tableActual).getIndexFromLabel(datos[datos.length - 1]);

        ((TableWithLabels)tableActual).addRow(new RowWithLabel(listDatos, nextRowIndex), datos[datos.length - 1]);
    }
}
