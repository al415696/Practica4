package es.uji.csv;

import es.uji.tables.Table;

import java.io.IOException;

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
