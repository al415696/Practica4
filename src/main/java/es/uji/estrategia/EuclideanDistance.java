package es.uji.estrategia;

import es.uji.algorithm.IncompatiblePositionFormatException;

import java.util.List;

public class EuclideanDistance implements Distance {
    @Override
    public double calculateDistance(List<Double> p, List<Double> q) throws IncompatiblePositionFormatException {

        if (p.size() != q.size()) throw new IncompatiblePositionFormatException(p, q);
        double sumatorioLocal = 0;
        for (int j = 0; j < p.size(); j++) {
            sumatorioLocal += Math.pow(p.get(j) - q.get(j), 2);
        }
        sumatorioLocal = Math.sqrt(sumatorioLocal);
        return sumatorioLocal;

    }
}