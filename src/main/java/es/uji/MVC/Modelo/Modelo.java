package es.uji.MVC.Modelo;

import es.uji.Algorithm.Algorithm;
import es.uji.CSV.CSV;
import es.uji.Estrategia.Distance;
import es.uji.Estrategia.EuclideanDistance;
import es.uji.Estrategia.ManhatanDistance;
import es.uji.Exceptions.SongNotInDataBaseException;
import es.uji.KNN.KNN;
import es.uji.Kmeans.Kmeans;
import es.uji.MVC.Vista.InformaVista;
import es.uji.Recomendacion.RecSys;
import es.uji.Tables.Table;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Modelo implements InterrogaModelo,CambioModelo {

    private InformaVista vista;

    private RecSys recsys;
    private Algorithm algorithm;
    private Distance distance;


    public void setVista(InformaVista vista) {
        this.vista = vista;
    }
    @Override
    public void train(){
     /*   recsys = new RecSys(algorithm);
        recsys.train(tables.get(algoritmo + "train"));
        recsys.run(tables.get(algoritmo + "test"), names);*/
    }
    @Override
    public ArrayList<String> getListaCanciones() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/files/songs_test_names.csv"));
        ArrayList<String> canciones = new ArrayList<>();
        while (scanner.hasNextLine()) canciones.add(scanner.nextLine());
        return canciones;
    }
@Override
    public ArrayList<String> getListaRecomendaciones(String nameLikedItem, int numRecommendations, String estrategia, String algoritmo) throws IOException, SongNotInDataBaseException {
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


    @Override
    public void cambiaAKNN() {
        algorithm = new KNN(distance);
    }

    @Override
    public void cambiaAKMeans() {
        algorithm = new Kmeans(15,10,7777777,distance);
    }

    @Override
    public void cambiaAEucliedean() {
        distance = new EuclideanDistance();
    }

    @Override
    public void cambiaAManhatan() {
        distance = new ManhatanDistance();
    }
}
