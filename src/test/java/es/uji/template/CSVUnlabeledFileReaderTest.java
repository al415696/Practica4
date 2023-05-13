package es.uji.Template;

import es.uji.csv.CSV;

import es.uji.csv.CSVUnlabeledFileReader;
import es.uji.tables.Table;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CSVUnlabeledFileReaderTest {

    String dirCsv = "src/files/miles_dollars.csv";
    CSVUnlabeledFileReader csvUnlabeled = new CSVUnlabeledFileReader(dirCsv);
    ArrayList<String> listaEsperada;
    Table tUnlabeled;
    ArrayList<Double> fila;
    Table primaryTable;
    Table secondaryTable;

    {
        try {
            tUnlabeled = csvUnlabeled.readTableFromSource();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        csvUnlabeled = new CSVUnlabeledFileReader(dirCsv);
        primaryTable = new Table();
        fila = new ArrayList<>();
        listaEsperada = new ArrayList<>();
    }

    @org.junit.jupiter.api.Test
    void getHeaderRegular() {
        listaEsperada.add("Miles");
        listaEsperada.add("Dollars");
        System.out.println("Probando header: miles_dollars.csv");
        System.out.println("\tesperado: " + listaEsperada);
        System.out.println("\tobtenido: " + tUnlabeled.getHeader());
        assertEquals(listaEsperada, tUnlabeled.getHeader());
    }
    @org.junit.jupiter.api.Test
    void getSizeRegular() {
        System.out.println("Probando tamaño: miles_dollars.csv");
        System.out.println("\tesperado: 25 ");
        System.out.println("\tobtenido: " + tUnlabeled.getSize());
        assertEquals(25, tUnlabeled.getSize());

    }

    @org.junit.jupiter.api.Test
    void numberColumnsRegular() {
        System.out.println("Probando Nº columnas: miles_dollars.csv");
        System.out.println("\tesperado: 2 ");
        System.out.println("\tobtenido: " + tUnlabeled.numberColumns());
        assertEquals(2, tUnlabeled.numberColumns());
    }
    @org.junit.jupiter.api.Test
    void getDataTable1() {
        fila.add(1211d);
        fila.add(1802d);

        assertEquals(fila, tUnlabeled.getRowAt(0).getData());
        System.out.println("Probando: Primera fila en miles_dollars.csv");
        System.out.println("     esperado: " + fila);
        System.out.println("     obtenido: " + tUnlabeled.getRowAt(0).getData());

    }

    @org.junit.jupiter.api.Test
    void getDataTable2() {
        fila.add(3643d);
        fila.add(5298d);

        assertEquals(fila, tUnlabeled.getRowAt(15).getData());
        System.out.println("Probando: Fila intermedia en miles_dollars.csv");
        System.out.println("     esperado: " + fila);
        System.out.println("     obtenido: " + tUnlabeled.getRowAt(15).getData());

    }

    @org.junit.jupiter.api.Test
    void getDataTable3() {
        fila.add(5439d);
        fila.add(6964d);

        assertEquals(fila, tUnlabeled.getRowAt(24).getData());
        System.out.println("Probando: Ultima fila en miles_dollars.csv");
        System.out.println("     esperado: " + fila);
        System.out.println("     obtenido: " + tUnlabeled.getRowAt(24).getData());
    }


    @Test
    void TestResultingTableSize() throws IOException {
        CSV csv = new CSV();
        secondaryTable = csv.readTable("src/files/miles_dollars.csv");
        primaryTable = csvUnlabeled.readTableFromSource();
        assertEquals(secondaryTable.getSize(), primaryTable.getSize());
    }
    @Test
    void TestReadTableFromSoure() throws IOException {
        CSV csv = new CSV();
        secondaryTable = csv.readTable("src/files/miles_dollars.csv");
        primaryTable = csvUnlabeled.readTableFromSource();
        for (int i = 0; i <primaryTable.getSize(); i++) {
            assertEquals(primaryTable.getRowAt(i).getData(),secondaryTable.getRowAt(i).getData());
        }
    }
    @Test
    void TestCLFRThrows(){

        try{
            CSVUnlabeledFileReader auxiliaryCUFR = new CSVUnlabeledFileReader("DireccionSinNingunSentido");
            auxiliaryCUFR.readTableFromSource();
        }
        catch (IOException e){
            assertEquals(FileNotFoundException.class, e.getClass());
        }

    }
}