package es.uji.estrategia;

import es.uji.exceptions.IncompatiblePositionFormatException;

import java.util.List;

public interface Distance {
    double calculateDistance(List<Double> p, List<Double> q) throws IncompatiblePositionFormatException;
}
