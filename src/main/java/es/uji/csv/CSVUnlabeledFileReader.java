package es.uji.csv;


import es.uji.csv.ReaderTemplate;
import es.uji.rows.Row;
import es.uji.tables.Table;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVUnlabeledFileReader extends ReaderTemplate {
    protected Scanner documento;

    public CSVUnlabeledFileReader(String source){
        super(source);
        tableActual = new Table();
    }
    @Override
    protected void openSource(String source) throws FileNotFoundException{
        documento = new Scanner(new File(source));
    }

    @Override
    protected void processHeaders(String headers) {
        String[] datos;
        ArrayList listDatos = new ArrayList();
        datos = headers.split(",");
        for (int i = 0; i < datos.length; i++) {
            listDatos.add(datos[i]);
        }
        tableActual.addHeader(listDatos);
    }

    @Override
    protected void processData(String data) {
        String[] datos;
        ArrayList<Double> listDatos = new ArrayList();
        datos = data.split(",");
        for (int i = 0; i < datos.length; i++) {
            listDatos.add(Double.parseDouble(datos[i]));
        }
        tableActual.addRow(new Row(listDatos));
    }

    @Override
    protected void closeSource() {
        documento.close();
    }

    @Override
    protected boolean hasMoreData() {
        return documento.hasNextLine();
    }

    @Override
    protected String getNextData() {
        return documento.nextLine();
    }
}
