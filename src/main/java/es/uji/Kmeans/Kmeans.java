package es.uji.Kmeans;

import es.uji.Algorithm.Algorithm;
import es.uji.Cluster.Cluster;
import es.uji.Estrategia.Distance;
import es.uji.Estrategia.DistanceClient;
import es.uji.Exceptions.IncompatiblePositionFormatException;
import es.uji.Rows.Row;
import es.uji.Tables.Table;

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
    public void train(Table datos) {
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
            for (int i = 0; i < numClusters; i++) {
                representantes.get(i).ClearGroup();
            }

            //Repite una vez por cada fila de la tabla
            for (int i = 0; i < datos.getSize(); i++) {
                //Empieza calculado la distancia al centro del primer cluster y asumiendo que es el más cercano
                indiceMayorCercania = 0;
                sumatorioLocal = 0;
                for (int j = 0; j < nColumnas; j++) {
                    sumatorioLocal += Math.pow(datos.getRowAt(i).getData().get(j) - representantes.get(0).getCentroide().getData().get(j), 2);
                }
                sumatorioLocal = Math.sqrt(sumatorioLocal);
                cercaniaMaxima = sumatorioLocal;
                //Mira la distancia para los demás representantes
                for (int j = 1; j < representantes.size(); j++) {
                    sumatorioLocal = 0;
                    for (int k = 0; k < nColumnas; k++) {
                        sumatorioLocal += Math.pow(datos.getRowAt(i).getData().get(k) - representantes.get(j).getCentroide().getData().get(k), 2);
                    }
                    sumatorioLocal = Math.sqrt(sumatorioLocal);

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
            int nColumnas = grupos.get(0).getCentroide().getData().size();

            int indiceMayorCercania = 0;
            double cercaniaMaxima;

            double sumatorioLocal = 0;
        /*for (int j = 0; j < nColumnas; j++) {
            sumatorioLocal += Math.pow(data.get(j) - grupos.get(0).getCentroide().getData().get(j), 2);
        }
        sumatorioLocal = Math.sqrt(sumatorioLocal);*/
            sumatorioLocal = distance.calculateDistance(data, grupos.get(0).getCentroide().getData());
            cercaniaMaxima = sumatorioLocal;

            for (int i = 1; i < grupos.size(); i++) {
                //sumatorioLocal = 0;

            /*for (int j = 0; j < nColumnas; j++) {
                sumatorioLocal += Math.pow(data.get(j) - grupos.get(i).getCentroide().getData().get(j), 2);
            }
            sumatorioLocal = Math.sqrt(sumatorioLocal);*/
                sumatorioLocal = distance.calculateDistance(data, grupos.get(i).getCentroide().getData());

                if (sumatorioLocal < cercaniaMaxima) {
                    cercaniaMaxima = sumatorioLocal;
                    indiceMayorCercania = i;
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
