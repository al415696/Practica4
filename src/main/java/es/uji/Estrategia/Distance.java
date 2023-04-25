package es.uji.Estrategia;

import es.uji.Exceptions.IncompatiblePositionFormatException;

import java.util.List;

public interface Distance {
    double calculateDistance(List<Double> p, List<Double> q) throws IncompatiblePositionFormatException;
}
