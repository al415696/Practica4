package es.uji.template;
import es.uji.csv.CSV;
import es.uji.csv.CSVLabeledFileReader;
import es.uji.csv.EmptyCSVDocumentException;
import es.uji.tables.TableWithLabels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;

public class CSVLabeledFileReaderTest {

    TableWithLabels primaryTable;
    TableWithLabels secondaryTable;
    CSVLabeledFileReader clfr = new CSVLabeledFileReader("src"+ File.separator +"files"+ File.separator +"iris.csv");
    CSV csv = new CSV();
    @BeforeEach
    void setUp() {
        primaryTable = new TableWithLabels();
    }
    @Test
    void TestResultingTableSize() throws IOException, EmptyCSVDocumentException {

        secondaryTable = csv.readTableWithLabels("src"+ File.separator +"files"+ File.separator +"iris.csv");
        primaryTable = (TableWithLabels) clfr.readTableFromSource();
        assertEquals(secondaryTable.getSize(), primaryTable.getSize());
    }
    @Test
    void TestReadTableFromSoure() throws IOException, EmptyCSVDocumentException {
        secondaryTable = csv.readTableWithLabels("src"+ File.separator +"files"+ File.separator +"iris.csv");
        primaryTable = (TableWithLabels) clfr.readTableFromSource();
        for (int i = 0; i <primaryTable.getSize(); i++) {
            assertEquals(primaryTable.getRowAt(i).getData(),secondaryTable.getRowAt(i).getData());
        }
    }
    @Test
    void TestCorrectNumOfLabelsOnTable() throws IOException, EmptyCSVDocumentException {
        primaryTable = (TableWithLabels) clfr.readTableFromSource();
        assertEquals(3 , primaryTable.getNumOfLabels());
    }
    @Test
    void TestCLFRThrows() throws EmptyCSVDocumentException {

        try{
            CSVLabeledFileReader auxiliaryCLFR = new CSVLabeledFileReader("DireccionSinNingunSentido");
            auxiliaryCLFR.readTableFromSource();
        }
        catch (IOException e){
            assertEquals(FileNotFoundException.class, e.getClass());
        }

    }

}