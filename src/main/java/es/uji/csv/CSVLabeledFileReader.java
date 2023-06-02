package es.uji.csv;


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
        ArrayList<Double> listDatos = new ArrayList();
        datos = data.split(",");

        for (int i = 0; i < datos.length - 1; i++) {
            listDatos.add(Double.parseDouble(datos[i]));
        }

        ((TableWithLabels)tableActual).addRow(listDatos,datos[datos.length-1]);
    }
}
