package es.uji.MVC.Controlador;

import es.uji.Algorithm.Algorithm;
import es.uji.CSV.CSV;
import es.uji.Estrategia.EuclideanDistance;
import es.uji.Estrategia.ManhatanDistance;
import es.uji.Exceptions.SongNotInDataBaseException;
import es.uji.KNN.KNN;
import es.uji.Kmeans.Kmeans;
import es.uji.Recomendacion.RecSys;
import es.uji.Tables.Table;

import java.io.*;
import java.security.spec.ECField;
import java.util.*;

public class Controlador {
    public static Arrays cancionesRecomendadas;
    private static RecSys recsys;

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
        if(Objects.equals(estrategia, "Manhattan")){
            algorithms.put("knn", new KNN(new ManhatanDistance()));
            algorithms.put("kmeans", new Kmeans(15, 200, 4321, new ManhatanDistance()));
        }else{
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


        List<String> names = readNames(ruta + sep + "songs_test_names.csv");

        recsys = new RecSys(algorithms.get(algoritmo));
        recsys.train(tables.get(algoritmo + "train"));
        recsys.run(tables.get(algoritmo + "test"), names);
        return (ArrayList<String>) recsys.recommend(nameLikedItem, numRecommendations);
        }

    private static List<String> readNames(String fileOfItemNames) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileOfItemNames));
        String line;
        List<String> names = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            names.add(line);
        }
        br.close();
        return names;
    }
    }

