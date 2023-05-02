package es.uji.MVC.Controlador;


import es.uji.Algorithm.Algorithm;
import es.uji.CSV.CSV;
import es.uji.Estrategia.EuclideanDistance;
import es.uji.Estrategia.ManhatanDistance;
import es.uji.Exceptions.SongNotInDataBaseException;
import es.uji.Algorithm.KNN.KNN;
import es.uji.Algorithm.Kmeans.Kmeans;
import es.uji.MVC.Modelo.CambioModelo;
import es.uji.MVC.Vista.InterrogaVista;
import es.uji.Recomendacion.RecSys;
import es.uji.Tables.Table;

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
            algorithms.put("knn", new KNN(new ManhatanDistance()));
            algorithms.put("kmeans", new Kmeans(15, 200, 4321, new ManhatanDistance()));
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
}