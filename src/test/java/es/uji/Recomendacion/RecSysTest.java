package es.uji.Recomendacion;

import es.uji.Algorithm.KNN.KNN;
import es.uji.Algorithm.Kmeans.Kmeans;
import es.uji.CSV.CSV;
import es.uji.Estrategia.EuclideanDistance;
import es.uji.Estrategia.ManhattanDistance;
import es.uji.Exceptions.SongNotInDataBaseException;
import es.uji.Tables.Table;
import es.uji.Tables.TableWithLabels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class RecSysTest {
    RecSys recSysKmeansManhattan = new RecSys<>(new Kmeans(15, 10, 7777777, new ManhattanDistance()));
    RecSys recSysKNNManhattan = new RecSys<>(new KNN(new ManhattanDistance()));
    RecSys recSysKmeansEuclidean = new RecSys<>(new Kmeans(15, 10, 7777777, new EuclideanDistance()));
    RecSys recSysKNNEuclidean = new RecSys<>(new KNN(new EuclideanDistance()));
    CSV csv = new CSV();
    Table tableTrain;
    TableWithLabels tableWithLabelsTrain;
    Table tableTest;
    ArrayList<String> names;

    {
        try {
            tableTrain = csv.readTable("src/files/songs_train_withoutnames.csv");
            tableTest = csv.readTable("src/files/songs_test_withoutnames.csv");
            tableWithLabelsTrain = csv.readTableWithLabels("src/files/songs_train_withoutnames.csv");
            Scanner scanner = new Scanner(new File("src/files/songs_test_names.csv"));
            names = new ArrayList<>();
            while (scanner.hasNextLine()) names.add(scanner.nextLine());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    void setUp() {
        recSysKmeansManhattan.train(tableTrain);
        recSysKNNManhattan.train(tableWithLabelsTrain);
        recSysKmeansEuclidean.train(tableTrain);
        recSysKNNEuclidean.train(tableWithLabelsTrain);
        recSysKmeansManhattan.run(tableTest,names);
        recSysKNNManhattan.run(tableTest,names);
        recSysKmeansEuclidean.run(tableTest,names);
        recSysKNNEuclidean.run(tableTest,names);
    }

    @Test

    void recommendExactly10KmeansMan() throws SongNotInDataBaseException {
        assertEquals(10, recSysKmeansManhattan.recommend("Tonight",10).size());
    }

    @Test

    void recommendsOfSameGroupKmeansMan() throws SongNotInDataBaseException {
        List recomendaciones = recSysKmeansManhattan.recommend("Headbangers:Dubstep/Riddim",4);

        for (int i = 0; i < recomendaciones.size(); i++) {
            assertEquals(true, recSysKmeansManhattan.getSongGroup((String) recomendaciones.get(i)) == recSysKmeansManhattan.getSongGroup("Headbangers:Dubstep/Riddim"));
        }

    }

    @Test
    void numRecommendations900KmeansMan() throws SongNotInDataBaseException {
        assertNotEquals(900, recSysKmeansManhattan.recommend("Tonight", 900).size());
    }
    @Test
    void recommendExactly10KNNMan() throws SongNotInDataBaseException {
        assertEquals(10, recSysKNNManhattan.recommend("Tonight",10).size());
    }

    @Test

    void recommendsOfSameGroupKNNMan() throws SongNotInDataBaseException {
        List recomendaciones = recSysKNNManhattan.recommend("Headbangers:Dubstep/Riddim",4);

        for (int i = 0; i < recomendaciones.size(); i++) {
            assertEquals(true, recSysKNNManhattan.getSongGroup((String) recomendaciones.get(i)) == recSysKNNManhattan.getSongGroup("Headbangers:Dubstep/Riddim"));
        }

    }

    @Test
    void numRecommendations900KNNMan() throws SongNotInDataBaseException {
        assertNotEquals(900, recSysKNNManhattan.recommend("Tonight", 900).size());
    }
    @Test
    void recommendExactly10KMeansEuc() throws SongNotInDataBaseException {
        assertEquals(10, recSysKmeansEuclidean.recommend("Tonight",10).size());
    }

    @Test

    void recommendsOfSameGroupKMeansMEuc() throws SongNotInDataBaseException {
        List recomendaciones = recSysKmeansEuclidean.recommend("Headbangers:Dubstep/Riddim",4);

        for (int i = 0; i < recomendaciones.size(); i++) {
            assertEquals(true, recSysKmeansEuclidean.getSongGroup((String) recomendaciones.get(i)) == recSysKmeansEuclidean.getSongGroup("Headbangers:Dubstep/Riddim"));
        }

    }

    @Test
    void numRecommendations900KMeansEuc() throws SongNotInDataBaseException {
        assertNotEquals(900, recSysKmeansEuclidean.recommend("Tonight", 900).size());
    }

    @Test
    void recommendExactly10KNNEuc() throws SongNotInDataBaseException {
        System.out.println(recSysKNNEuclidean.recommend("Tonight",10));
        assertEquals(10, recSysKNNEuclidean.recommend("Tonight",10).size());
    }

    @Test

    void recommendsOfSameGroupKNNEuc() throws SongNotInDataBaseException {
        List recomendaciones = recSysKNNEuclidean.recommend("Headbangers:Dubstep/Riddim",4);

        for (int i = 0; i < recomendaciones.size(); i++) {
            assertEquals(true, recSysKNNEuclidean.getSongGroup((String) recomendaciones.get(i)) == recSysKNNEuclidean.getSongGroup("Headbangers:Dubstep/Riddim"));
        }

    }

    @Test
    void numRecommendations900KNNEuc() throws SongNotInDataBaseException {
        assertNotEquals(900, recSysKNNEuclidean.recommend("Tonight", 900).size());
    }
    /*
    @Test
    void SongNotInDatabase(){
        recSysKmeans.run(tableTest, names);
        Exception exep = assertThrows(SongNotInDataBaseException.class, () -> recSysKmeans.recommend("Top 10 Sea Shanties of all time", 10));
        assertEquals(SongNotInDataBaseException.class, exep.getClass());
    }

     */
}