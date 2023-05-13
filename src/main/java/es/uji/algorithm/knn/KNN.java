package es.uji.algorithm.knn;

import es.uji.algorithm.Algorithm;
import es.uji.estrategia.Distance;
import es.uji.estrategia.DistanceClient;
import es.uji.exceptions.IncompatiblePositionFormatException;
import es.uji.rows.RowWithLabel;
import es.uji.tables.TableWithLabels;

import java.util.List;

public class KNN implements Algorithm<TableWithLabels, List<Double>, Integer>, DistanceClient {
    private TableWithLabels tabla;
    private Distance distance;
    public KNN(Distance distance) {
        this.distance = distance;
    }

    public void train(TableWithLabels data) {
        tabla = data;
    }

    public Integer estimate(List<Double> data) {
        try {
            int nColumnas = tabla.getHeader().size() - 1;
            if (nColumnas != data.size())
                return -1;
            RowWithLabel rowActual;

            int indiceMayorCercania = 0;
            double cercaniaMaxima;

            double sumatorioLocal = 0;

            sumatorioLocal = distance.calculateDistance(data, tabla.getRowAt(1).getData());
            cercaniaMaxima = sumatorioLocal;

            for (int i = 1; i < tabla.getSize(); i++) {
                rowActual = tabla.getRowAt(i);
                sumatorioLocal = distance.calculateDistance(data, rowActual.getData());

                if (sumatorioLocal < cercaniaMaxima) {
                    cercaniaMaxima = sumatorioLocal;
                    indiceMayorCercania = rowActual.getNumberClass();
                }

            }
            return indiceMayorCercania;
        }
        catch (IncompatiblePositionFormatException e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void setDistance(Distance distance) {
        this.distance = distance;
    }
}
