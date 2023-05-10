package es.uji.kmeans;

import es.uji.algorithm.kmeans.Kmeans;
import es.uji.csv.CSV;
import es.uji.estrategia.ManhattanDistance;
import es.uji.tables.Table;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class KmeansTest {
    Kmeans kmeans;
    CSV csv = new CSV();
    Table table;
    ArrayList<Double> row1 = new ArrayList<>();
    ArrayList<Double> row2 = new ArrayList<>();

    {
        try {
            table = csv.readTableWithLabels("src/files/iris.csv");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        row1 = new ArrayList<>();
        row2 = new ArrayList<>();

    }

    @Test
    void train() {
    }

    @Test
    void estimateMismoDato() {
        kmeans = new Kmeans(3, 6, 7777777,new ManhattanDistance());
        kmeans.train(table);
        row1.add(6.7);
        row1.add(2.5);
        row1.add(5.8);
        row1.add(1.8);
        row2.addAll(row1);
        assertEquals(kmeans.estimate(row1), kmeans.estimate(row2));
    }

    @Test
    void estimateDiferenteDato() {
        kmeans = new Kmeans(3, 6, 7777777, new ManhattanDistance());
        kmeans.train(table);
        row1.add(7.9);
        row1.add(3.8);
        row1.add(6.4);
        row1.add(2.0);

        row2.add(4.3);
        row2.add(3.0);
        row2.add(1.1);
        row2.add(0.1);
        assertNotEquals(kmeans.estimate(row1), kmeans.estimate(row2));
    }

    @Test
    void estimateMismoGrupo1() {
        kmeans = new Kmeans(3, 6, 7777777, new ManhattanDistance());
        kmeans.train(table);
        row1.add(6.7);
        row1.add(2.5);
        row1.add(5.8);
        row1.add(1.8);

        row2.add(5.8);
        row2.add(2.7);
        row2.add(5.1);
        row2.add(1.9);
        assertEquals(kmeans.estimate(row1), kmeans.estimate(row2));
    }

    @Test
    void estimateMismoGrupo2() {
        kmeans = new Kmeans(3, 4, 7777777, new ManhattanDistance());
        kmeans.train(table);
        row1.add(7.0);
        row1.add(3.2);
        row1.add(4.7);
        row1.add(1.4);

        row2.add(6.4);
        row2.add(3.2);
        row2.add(4.5);
        row2.add(1.5);
        assertEquals(kmeans.estimate(row1), kmeans.estimate(row2));
    }

    @Test
    void estimateDiferenteGrupo1() {
        kmeans = new Kmeans(3, 6, 7777777, new ManhattanDistance());
        kmeans.train(table);
        row1.add(7.0);
        row1.add(3.2);
        row1.add(4.7);
        row1.add(1.4);

        row2.add(5.4);
        row2.add(3.7);
        row2.add(1.6);
        row2.add(1.9);
        assertNotEquals(kmeans.estimate(row2), kmeans.estimate(row1));
    }

    @Test
    void estimateDiferenteGrupo2() {
        kmeans = new Kmeans(3, 6, 7777777, new ManhattanDistance());
        kmeans.train(table);
        row1.add(5.1);
        row1.add(3.5);
        row1.add(1.4);
        row1.add(0.2);

        row2.add(5.8);
        row2.add(2.7);
        row2.add(5.1);
        row2.add(1.9);
        assertNotEquals(kmeans.estimate(row2), kmeans.estimate(row1));
    }
}