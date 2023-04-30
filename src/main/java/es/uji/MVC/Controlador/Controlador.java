package es.uji.MVC.Controlador;

import es.uji.CSV.CSV;
import es.uji.Estrategia.ManhatanDistance;
import es.uji.Exceptions.SongNotInDataBaseException;
import es.uji.KNN.KNN;
import es.uji.Recomendacion.RecSys;
import es.uji.Tables.Table;
import es.uji.Tables.TableWithLabels;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Controlador {
    public static Arrays cancionesRecomendadas;

    public static ArrayList<String> getListaCanciones() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/files/songs_test_names.csv"));
        ArrayList<String> canciones = new ArrayList<>();
        while (scanner.hasNextLine()) canciones.add(scanner.nextLine());
        return canciones;
    }

    public static ArrayList<String> getListaRecomendaciones(String nameLikedItem, int numRecommendations) throws FileNotFoundException, SongNotInDataBaseException {
        /*Scanner scanner = new Scanner(new File("src/files/songs_test_names.csv"));
        ArrayList<String> canciones = new ArrayList<>();
        while (scanner.hasNextLine()) canciones.add(scanner.nextLine());
        return canciones;*/
        RecSys recSys = new RecSys<>(new KNN(new ManhatanDistance()));
        CSV csv = new CSV();
        Table tableTrain;
        Table tableTest;
        ArrayList<String> names;
        try {
                tableTrain = csv.readTableWithLabels("src/files/songs_train_withoutnames.csv");
                tableTest = csv.readTableWithLabels("src/files/songs_test_withoutnames.csv");
                Scanner scanner = new Scanner(new File("src/files/songs_test_names.csv"));
                names = new ArrayList<>();
                while (scanner.hasNextLine()) names.add(scanner.nextLine());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            recSys.train(tableTrain);
            recSys.run(tableTest,names);
        System.out.println(nameLikedItem + numRecommendations);
        System.out.println(recSys.recommend("Tonight",numRecommendations));
            return (ArrayList<String>) recSys.recommend("Tonight",numRecommendations);
        }
    }

