package es.uji.algorithm.kmeans;

import es.uji.algorithm.Algorithm;
import es.uji.algorithm.IncompatiblePositionFormatException;
import es.uji.algorithm.kmeans.cluster.Cluster;
import es.uji.estrategia.Distance;
import es.uji.estrategia.DistanceClient;
import es.uji.tables.Row;
import es.uji.tables.Table;

import java.util.*;

public class Kmeans implements Algorithm<Table, List<Double>, Integer>, DistanceClient {
    private int numClusters;
    private int numIterations;
    private long seed;
    private Distance distance;

    private List<Cluster> grupos = new ArrayList<>();


    public Kmeans(int numClusters, int numIterations, long seed, Distance distance) {

        this.numClusters = numClusters;
        this.numIterations = numIterations;
        this.seed = seed;
        this.distance = distance;
    }

    @Override
    public void train(Table datos) throws IncompatiblePositionFormatException {
        Random random = new Random(seed);
        List<Cluster> representantes = new ArrayList<>();
        HashMap<Integer, Row> indicesCentroidesOriginales = new HashMap<>();
        int candidatoRepresentante;

        //Se crea los clusters aleatorios originales
        for (int i = 0; i < numClusters; i++) {
            candidatoRepresentante = random.nextInt(datos.getSize());
            while (indicesCentroidesOriginales.containsValue(datos.getRowAt(candidatoRepresentante))) {
                candidatoRepresentante = random.nextInt(datos.getSize());
            }
            representantes.add(new Cluster(i, datos.getRowAt(candidatoRepresentante)));
        }


        int nColumnas = datos.numberColumns();
        //Repite todo el proceso las interaciones que queden

        int indiceMayorCercania;
        double cercaniaMaxima;
        double sumatorioLocal;
        for (int t = 0; t < numIterations - 1; t++) {

            //Borra los grupos al principio de cada iteración para reasignarlos
            clearAllGroups(representantes);

            //Repite una vez por cada fila de la tabla
            for (int i = 0; i < datos.getSize(); i++) {
                //Empieza calculado la distancia al centro del primer cluster y asumiendo que es el más cercano
                indiceMayorCercania = 0;
                sumatorioLocal = distance.calculateDistance(datos.getRowAt(i).getData(), representantes.get(0).getCentroide().getData());
                cercaniaMaxima = sumatorioLocal;

                //Mira la distancia para los demás representantes
                for (int j = 1; j < representantes.size(); j++) {
                    sumatorioLocal = distance.calculateDistance(datos.getRowAt(i).getData(), representantes.get(j).getCentroide().getData());
                    if (sumatorioLocal < cercaniaMaxima) {
                        cercaniaMaxima = sumatorioLocal;
                        indiceMayorCercania = j;
                    }
                }
                representantes.get(indiceMayorCercania).addRow(datos.getRowAt(i));
            }

            for (int i = 0; i < numClusters; i++) {
                representantes.get(i).setCentroide(calcularNuevoCentroide(representantes.get(i), nColumnas));
            }

        }


        grupos = representantes;

    }

    private void clearAllGroups(List<Cluster> representantes) {
        for (int i = 0; i < numClusters; i++) {
            representantes.get(i).ClearGroup();
        }
    }

    private Row calcularNuevoCentroide(Cluster viejoCluster, int numColumnas) {
        List<Double> nuevoCentroide = new ArrayList<>();
        double[] mediaColumna = new double[numColumnas];
        for (Row filaActual : viejoCluster.getGrupo()) {
            for (int j = 0; j < numColumnas; j++) {
                mediaColumna[j] += filaActual.getData().get(j);
            }
        }

        for (int i = 0; i < numColumnas; i++) {
            nuevoCentroide.add(mediaColumna[i] / viejoCluster.getGrupo().size());
        }
        return new Row(nuevoCentroide);
    }

    @Override
    public Integer estimate(List<Double> data) {
        try {
            if (grupos.size() == 0) throw new MissingResourceException("aun no se ha entrenado", "estimate", "7");

            int indiceMayorCercania = 0;
            double cercaniaMaxima;

            double sumatorioLocal;
            sumatorioLocal = distance.calculateDistance(data, grupos.get(0).getCentroide().getData());
            cercaniaMaxima = sumatorioLocal;

            for (int i = 1; i < grupos.size(); i++) {
                sumatorioLocal = distance.calculateDistance(data, grupos.get(i).getCentroide().getData());

                if (sumatorioLocal < cercaniaMaxima) {
                    cercaniaMaxima = sumatorioLocal;
                    indiceMayorCercania = i;
                }

            }
            return indiceMayorCercania;
        } catch (IncompatiblePositionFormatException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void setDistance(Distance distance) {
        this.distance = distance;
    }
}
