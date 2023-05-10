package es.uji.row;

import es.uji.csv.CSV;
import es.uji.tables.TableWithLabels;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RowTest {
    String dirIris = "src/files/iris.csv";

    CSV csv = new CSV();
    TableWithLabels tIris;

    {
        try {
            tIris = csv.readTableWithLabels(dirIris);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    ArrayList<Double> fila;

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws FileNotFoundException {
        fila = new ArrayList<>();
    }

    @org.junit.jupiter.api.Test
    void estimateExacto1() {
        System.out.println("Probando: Primera fila en iris.csv");
        System.out.println("     esperado: 0 ");
        System.out.println("     obtenido: " + tIris.getRowAt(0).getNumberClass());

        assertEquals(0, tIris.getRowAt(0).getNumberClass());
    }

    @org.junit.jupiter.api.Test
    void estimateExacto2() throws FileNotFoundException {
        System.out.println("Probando: Fila intermedia en iris.csv");
        System.out.println("     esperado: 1 ");
        System.out.println("     obtenido: " + tIris.getRowAt(70).getNumberClass());
        assertEquals(1, tIris.getRowAt(78).getNumberClass());
    }

    @org.junit.jupiter.api.Test
    void estimateExacto3() {
        System.out.println("Probando: Última fila en iris.csv");
        System.out.println("     esperado: 2 ");
        System.out.println("     obtenido: " + tIris.getRowAt(149).getNumberClass());
        assertEquals(2, tIris.getRowAt(149).getNumberClass());
    }
}
