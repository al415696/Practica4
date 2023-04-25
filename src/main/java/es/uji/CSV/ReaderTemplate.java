package es.uji.CSV;

import es.uji.Tables.Table;

import java.io.IOException;
import java.util.Scanner;

public abstract class ReaderTemplate {
    protected String documentoSource;
    protected Table tableActual;
    public ReaderTemplate(String source){
        documentoSource = source;
    }
    protected abstract void openSource(String source) throws IOException;
    protected abstract void processHeaders(String headers);
    protected abstract void processData(String data);
    protected abstract void closeSource();
    protected abstract boolean hasMoreData();
    protected abstract String getNextData();
    public final Table readTableFromSource() throws IOException{
        tableActual = new Table();
        openSource(documentoSource);
        if (hasMoreData()){
            processHeaders(getNextData());
        }
        else {
            throw new RuntimeException();
        }
        while (hasMoreData()){
            processData(getNextData());
        }
        closeSource();
        return tableActual;
    }
}