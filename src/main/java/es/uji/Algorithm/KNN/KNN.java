package es.uji.Algorithm.KNN;

import es.uji.Algorithm.Algorithm;
import es.uji.Estrategia.Distance;
import es.uji.Estrategia.DistanceClient;
import es.uji.Exceptions.IncompatiblePositionFormatException;
import es.uji.Rows.RowWithLabel;
import es.uji.Tables.TableWithLabels;

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
        /*for (int j = 0; j < nColumnas; j++) {
            sumatorioLocal += Math.pow(data.get(j) - tabla.getRowAt(1).getData().get(j), 2);

        }
        sumatorioLocal = Math.sqrt(sumatorioLocal);*/

            sumatorioLocal = distance.calculateDistance(data, tabla.getRowAt(1).getData());
            cercaniaMaxima = sumatorioLocal;

            for (int i = 1; i < tabla.getSize(); i++) {
                //sumatorioLocal = 0;
                rowActual = tabla.getRowAt(i);
            /*for (int j = 0; j < nColumnas; j++) {
                sumatorioLocal += Math.pow(data.get(j) - rowActual.getData().get(j), 2);
            }
            sumatorioLocal = Math.sqrt(sumatorioLocal);*/
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
