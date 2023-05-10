package es.uji.table;

import es.uji.csv.CSV;
import es.uji.tables.Table;
import es.uji.tables.TableWithLabels;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TableTest {
    CSV csv = new CSV();
    ArrayList<String> listaEsperada;
    TableWithLabels tableIris;
    Table tableExpress;

    {
        try {
            tableIris = csv.readTableWithLabels("src/files/iris.csv");
            tableExpress = csv.readTable("src/files/miles_dollars.csv");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        listaEsperada = new ArrayList<>();
    }

    @org.junit.jupiter.api.Test
    void wrongFileTestRegular() {

        System.out.println("Probando excepcion: iris.csv");
        Exception exep = assertThrows(FileNotFoundException.class, () -> csv.readTable("/src/sinSentido"));
        assertEquals(FileNotFoundException.class, exep.getClass());
    }

    @org.junit.jupiter.api.Test
    void wrongFileTestLabels() {

        System.out.println("Probando excepcion en WithLabels: iris.csv");
        Exception exep = assertThrows(FileNotFoundException.class, () -> csv.readTableWithLabels("/src/sinSentido"));
        assertEquals(FileNotFoundException.class, exep.getClass());
    }

    @org.junit.jupiter.api.Test
    void getHeaderLabels() {
        listaEsperada.add("sepal length");
        listaEsperada.add("sepal width");
        listaEsperada.add("petal length");
        listaEsperada.add("petal width");
        listaEsperada.add("class");
        System.out.println("Probando header: iris.csv");
        System.out.println("\tesperado: " + listaEsperada);
        System.out.println("\tobtenido: " + tableIris.getHeader());
        assertEquals(listaEsperada, tableIris.getHeader());
    }

    @org.junit.jupiter.api.Test
    void getHeaderRegular() {
        listaEsperada.add("Miles");
        listaEsperada.add("Dollars");
        System.out.println("Probando header: miles_dollars.csv");
        System.out.println("\tesperado: " + listaEsperada);
        System.out.println("\tobtenido: " + tableExpress.getHeader());
        assertEquals(listaEsperada, tableExpress.getHeader());
    }

    @org.junit.jupiter.api.Test
    void getSizeLabels() {

        assertEquals(150, tableIris.getSize());
        System.out.println("Probando tamaño: iris.csv");
        System.out.println("\tesperado: 150 ");
        System.out.println("\tobtenido: " + tableIris.getSize());

    }

    @org.junit.jupiter.api.Test
    void getSizeRegular() {
        System.out.println("Probando tamaño: miles_dollars.csv");
        System.out.println("\tesperado: 25 ");
        System.out.println("\tobtenido: " + tableExpress.getSize());
        assertEquals(25, tableExpress.getSize());

    }

    @org.junit.jupiter.api.Test
    void numberColumnsLabel() {
        System.out.println("Probando Nº columnas: iris.csv");
        System.out.println("\tesperado: 4 ");
        System.out.println("\tobtenido: " + tableIris.numberColumns());
        assertEquals(4, tableIris.numberColumns());

    }

    @org.junit.jupiter.api.Test
    void numberColumnsRegular() {
        System.out.println("Probando Nº columnas: miles_dollars.csv");
        System.out.println("\tesperado: 2 ");
        System.out.println("\tobtenido: " + tableExpress.numberColumns());
        assertEquals(2, tableExpress.numberColumns());
    }
}
