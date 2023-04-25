package es.uji.CSV;

import es.uji.Rows.Row;
import es.uji.Rows.RowWithLabel;
import es.uji.Tables.TableWithLabels;

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
            nextRowIndex = ((TableWithLabels)tableActual).getNextIndex();
        else
            nextRowIndex = ((TableWithLabels)tableActual).getIndexFromLabel(datos[datos.length - 1]);

        ((TableWithLabels)tableActual).addRow(new RowWithLabel(listDatos, nextRowIndex), datos[datos.length - 1]);
    }
}
