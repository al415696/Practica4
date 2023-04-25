package es.uji.Row;

import es.uji.CSV.CSV;
import es.uji.Tables.Table;
import es.uji.Tables.TableWithLabels;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RowWithLabelsTest {
    String dirIris = "src/files/iris.csv";
    String dirExpress = "src/files/miles_dollars.csv";

    CSV csv = new CSV();
    TableWithLabels tIris;
    Table tExpress;

    {
        try {
            tIris = csv.readTableWithLabels(dirIris);
            tExpress = csv.readTable(dirExpress);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    ArrayList<Double> fila;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        fila = new ArrayList<>();
    }

    @org.junit.jupiter.api.Test
    void getDataTableWithLabel1() {
        fila.add(5.1);
        fila.add(3.5);
        fila.add(1.4);
        fila.add(0.2);

        assertEquals(fila, tIris.getRowAt(0).getData());
        System.out.println("Probando: Primera fila en iris.csv");
        System.out.println("     esperado: " + fila);
        System.out.println("     obtenido: " + tIris.getRowAt(0).getData());

    }

    @org.junit.jupiter.api.Test
    void getDataTableWithLabel2() {
        fila.add(6.0);
        fila.add(2.9);
        fila.add(4.5);
        fila.add(1.5);

        assertEquals(fila, tIris.getRowAt(78).getData());
        System.out.println("Probando: Última fila en iris.csv");
        System.out.println("     esperado: " + fila);
        System.out.println("     obtenido: " + tIris.getRowAt(78).getData());
    }

    @org.junit.jupiter.api.Test
    void getDataTableWithLabel3() {
        fila.add(5.9);
        fila.add(3.0);
        fila.add(5.1);
        fila.add(1.8);

        assertEquals(fila, tIris.getRowAt(149).getData());
        System.out.println("Probando: Última fila en iris.csv");
        System.out.println("     esperado: " + fila);
        System.out.println("     obtenido: " + tIris.getRowAt(149).getData());
    }

    @org.junit.jupiter.api.Test
    void getDataTable1() {
        fila.add(1211d);
        fila.add(1802d);

        assertEquals(fila, tExpress.getRowAt(0).getData());
        System.out.println("Probando: Primera fila en miles_dollars.csv");
        System.out.println("     esperado: " + fila);
        System.out.println("     obtenido: " + tExpress.getRowAt(0).getData());

    }

    @org.junit.jupiter.api.Test
    void getDataTable2() {
        fila.add(3643d);
        fila.add(5298d);

        assertEquals(fila, tExpress.getRowAt(15).getData());
        System.out.println("Probando: Fila intermedia en miles_dollars.csv");
        System.out.println("     esperado: " + fila);
        System.out.println("     obtenido: " + tExpress.getRowAt(15).getData());

    }

    @org.junit.jupiter.api.Test
    void getDataTable3() {
        fila.add(5439d);
        fila.add(6964d);

        assertEquals(fila, tExpress.getRowAt(24).getData());
        System.out.println("Probando: Ultima fila en miles_dollars.csv");
        System.out.println("     esperado: " + fila);
        System.out.println("     obtenido: " + tExpress.getRowAt(0).getData());
    }
}
