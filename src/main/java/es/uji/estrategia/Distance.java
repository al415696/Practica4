package es.uji.estrategia;

import es.uji.algorithm.IncompatiblePositionFormatException;

import java.util.List;

public interface Distance {
    double calculateDistance(List<Double> p, List<Double> q) throws IncompatiblePositionFormatException;
}
