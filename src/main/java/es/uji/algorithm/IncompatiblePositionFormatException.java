package es.uji.algorithm;

import java.util.List;

public class IncompatiblePositionFormatException extends Exception {
    public IncompatiblePositionFormatException(List p, List q) {
        super("p es de tamaño " + p.size() + " y q es de tamaño " + q.size());
    }
}
