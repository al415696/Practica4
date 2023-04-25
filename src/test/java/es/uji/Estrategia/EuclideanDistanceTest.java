package es.uji.Estrategia;

import es.uji.Exceptions.IncompatiblePositionFormatException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EuclideanDistanceTest {
    EuclideanDistance ed = new EuclideanDistance();
    List<Double> p = new ArrayList<>();
    List<Double> q = new ArrayList<>();
    @AfterEach
    void tearDown() {
        p = new ArrayList<>();
        q = new ArrayList<>();
    }
    @Test
    void calculateDistance3Columns() throws IncompatiblePositionFormatException{
        p.add(1d);
        p.add(2d);
        p.add(3d);
        q.add(2d);
        q.add(3d);
        q.add(4d);
        assertEquals(Math.sqrt(3),ed.calculateDistance(p,q));
    }
    @Test
    void calculateDistance2Columns() throws IncompatiblePositionFormatException{
        p.add(1d);
        p.add(2d);
        q.add(2d);
        q.add(3d);
        assertEquals(Math.sqrt(2),ed.calculateDistance(p,q));
    }
    @Test
    void argumentSizeError() {
        p.add(1d);
        p.add(2d);
        p.add(3d);
        q.add(2d);
        q.add(3d);
        Exception exep = assertThrows(IncompatiblePositionFormatException.class, () -> ed.calculateDistance(p,q));
        assertEquals(IncompatiblePositionFormatException.class, exep.getClass());
    }
}