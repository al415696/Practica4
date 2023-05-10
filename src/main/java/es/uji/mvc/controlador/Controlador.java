package es.uji.mvc.controlador;


import es.uji.algorithm.Algorithm;
import es.uji.csv.CSV;
import es.uji.estrategia.EuclideanDistance;
import es.uji.estrategia.ManhattanDistance;
import es.uji.exceptions.SongNotInDataBaseException;
import es.uji.algorithm.knn.KNN;
import es.uji.algorithm.kmeans.Kmeans;
import es.uji.mvc.modelo.CambioModelo;
import es.uji.mvc.vista.InterrogaVista;
import es.uji.recomendacion.RecSys;
import es.uji.tables.Table;

import java.io.*;
import java.util.*;

public class Controlador implements Controller {
    private CambioModelo modelo;
    private InterrogaVista vista;
    private static RecSys recsys;

    public void setModelo(CambioModelo modelo) {
        this.modelo = modelo;
    }

    public void setVista(InterrogaVista vista) {
        this.vista = vista;

    }
    public static ArrayList<String> getListaCanciones() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/files/songs_test_names.csv"));
        ArrayList<String> canciones = new ArrayList<>();
        while (scanner.hasNextLine()) canciones.add(scanner.nextLine());
        return canciones;
    }

    public static ArrayList<String> getListaRecomendaciones(String nameLikedItem, int numRecommendations, String estrategia, String algoritmo) throws IOException, SongNotInDataBaseException {
        String sep = System.getProperty("file.separator");
        String ruta = "src/files/";


        Map<String, String> filenames = new HashMap<>();
        filenames.put("knn" + "train", ruta + sep + "songs_train.csv");
        filenames.put("knn" + "test", ruta + sep + "songs_test.csv");
        filenames.put("kmeans" + "train", ruta + sep + "songs_train_withoutnames.csv");
        filenames.put("kmeans" + "test", ruta + sep + "songs_test_withoutnames.csv");


        Map<String, Algorithm> algorithms = new HashMap<>();
        if (Objects.equals(estrategia, "Manhattan")) {
            algorithms.put("knn", new KNN(new ManhattanDistance()));
            algorithms.put("kmeans", new Kmeans(15, 200, 4321, new ManhattanDistance()));
        } else {
            algorithms.put("knn", new KNN(new EuclideanDistance()));
            algorithms.put("kmeans", new Kmeans(15, 200, 4321, new EuclideanDistance()));
        }

        Map<String, Table> tables = new HashMap<>();
        String[] stages = {"train", "test"};
        CSV csv = new CSV();
        for (String stage : stages) {
            tables.put("knn" + stage, csv.readTableWithLabels(filenames.get("knn" + stage)));
            tables.put("kmeans" + stage, csv.readTable(filenames.get("kmeans" + stage)));
        }


        List<String> names = getListaCanciones();

        recsys = new RecSys(algorithms.get(algoritmo));
        recsys.train(tables.get(algoritmo + "train"));
        recsys.run(tables.get(algoritmo + "test"), names);
        return (ArrayList<String>) recsys.recommend(nameLikedItem, numRecommendations);
    }

    @Override
    public void selectAlgorithm(int indice) {
        modelo.selectAlgorithm(indice);
    }

    @Override
    public void selectDistance(int indice) {
        modelo.selectDistance(indice);
    }
}