package es.uji.estrategia;

import es.uji.exceptions.IncompatiblePositionFormatException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManhatanDistanceTest {
    ManhattanDistance md = new ManhattanDistance();
    List<Double> p = new ArrayList<>();
    List<Double> q = new ArrayList<>();
    @AfterEach
    void tearDown() {
        p = new ArrayList<>();
        q = new ArrayList<>();
    }

    @Test
    void calculateDistance3Columns() throws IncompatiblePositionFormatException {
        p.add(1d);
        p.add(2d);
        p.add(3d);
        q.add(2d);
        q.add(3d);
        q.add(4d);
        assertEquals(3,md.calculateDistance(p,q));
    }
    @Test
    void calculateDistance2Columns() throws IncompatiblePositionFormatException{
        p.add(1d);
        p.add(2d);
        q.add(2d);
        q.add(3d);
        assertEquals(2,md.calculateDistance(p,q));
    }
    @Test
    void argumentSizeError() {
        p.add(1d);
        p.add(2d);
        p.add(3d);
        q.add(2d);
        q.add(3d);
        Exception exep = assertThrows(IncompatiblePositionFormatException.class, () -> md.calculateDistance(p,q));
        assertEquals(IncompatiblePositionFormatException.class, exep.getClass());
    }
}