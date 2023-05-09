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
    RecSys recSysKNNManhatan = new RecSys<>(new KNN(new ManhattanDistance()));
    RecSys recSysKmeansEuclidean = new RecSys<>(new Kmeans(15, 10, 7777777, new EuclideanDistance()));
    RecSys recSysKNNEuclidean = new RecSys<>(new KNN(new EuclideanDistance()));
    CSV csv = new CSV();
    TableWithLabels tableTrain;
    TableWithLabels tableTest;

    ArrayList<String> names;

    {
        try {
            tableTrain = csv.readTableWithLabels("src/files/songs_train_withoutnames.csv");
            tableTest = csv.readTableWithLabels("src/files/songs_test_withoutnames.csv");
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
        recSysKNNManhatan.train(tableTrain);
        recSysKmeansEuclidean.train(tableTrain);
        recSysKNNEuclidean.train(tableTrain);
    }

    @Test

    void recommendExactly10KmeansMan() throws SongNotInDataBaseException {
        recSysKmeansManhattan.run(tableTest,names);
        assertEquals(10, recSysKmeansManhattan.recommend("Tonight",10).size());
    }

    @Test

    void recommendsOfSameGroupKmeansMan() throws SongNotInDataBaseException {
        recSysKmeansManhattan.run(tableTest,names);
        List recomendaciones = recSysKmeansManhattan.recommend("Headbangers:Dubstep/Riddim",4);

        for (int i = 0; i < recomendaciones.size(); i++) {
            assertEquals(true, recSysKmeansManhattan.getSongGroup((String) recomendaciones.get(i)) == recSysKmeansManhattan.getSongGroup("Headbangers:Dubstep/Riddim"));
        }

    }

    @Test
    void numRecommendations900KmeansMan() throws SongNotInDataBaseException {
        recSysKmeansManhattan.run(tableTest, names);
        assertNotEquals(900, recSysKmeansManhattan.recommend("Tonight", 900).size());
    }
    @Test
    void recommendExactly10KNNMan() throws SongNotInDataBaseException {
        recSysKNNManhatan.run(tableTest,names);
        assertEquals(10, recSysKNNManhatan.recommend("Tonight",10).size());
    }

    @Test

    void recommendsOfSameGroupKNNMan() throws SongNotInDataBaseException {
        recSysKNNManhatan.run(tableTest,names);
        List recomendaciones = recSysKNNManhatan.recommend("Headbangers:Dubstep/Riddim",4);

        for (int i = 0; i < recomendaciones.size(); i++) {
            assertEquals(true, recSysKNNManhatan.getSongGroup((String) recomendaciones.get(i)) == recSysKNNManhatan.getSongGroup("Headbangers:Dubstep/Riddim"));
        }

    }

    @Test
    void numRecommendations900KNNMan() throws SongNotInDataBaseException {
        recSysKNNManhatan.run(tableTest, names);
        assertNotEquals(900, recSysKNNManhatan.recommend("Tonight", 900).size());
    }
    @Test
    void recommendExactly10KMeansEuc() throws SongNotInDataBaseException {
        recSysKmeansEuclidean.run(tableTest,names);
        assertEquals(10, recSysKmeansEuclidean.recommend("Tonight",10).size());
    }

    @Test

    void recommendsOfSameGroupKMeansMEuc() throws SongNotInDataBaseException {
        recSysKmeansEuclidean.run(tableTest,names);
        List recomendaciones = recSysKmeansEuclidean.recommend("Headbangers:Dubstep/Riddim",4);

        for (int i = 0; i < recomendaciones.size(); i++) {
            assertEquals(true, recSysKmeansEuclidean.getSongGroup((String) recomendaciones.get(i)) == recSysKmeansEuclidean.getSongGroup("Headbangers:Dubstep/Riddim"));
        }

    }

    @Test
    void numRecommendations900KMeansEuc() throws SongNotInDataBaseException {
        recSysKmeansEuclidean.run(tableTest, names);
        assertNotEquals(900, recSysKmeansEuclidean.recommend("Tonight", 900).size());
    }

    @Test
    void recommendExactly10KNNEuc() throws SongNotInDataBaseException {
        recSysKNNEuclidean.run(tableTest,names);
        assertEquals(10, recSysKNNEuclidean.recommend("Tonight",10).size());
    }

    @Test

    void recommendsOfSameGroupKNNEuc() throws SongNotInDataBaseException {
        recSysKNNEuclidean.run(tableTest,names);
        List recomendaciones = recSysKNNManhatan.recommend("Headbangers:Dubstep/Riddim",4);

        for (int i = 0; i < recomendaciones.size(); i++) {
            assertEquals(true, recSysKNNEuclidean.getSongGroup((String) recomendaciones.get(i)) == recSysKNNEuclidean.getSongGroup("Headbangers:Dubstep/Riddim"));
        }

    }

    @Test
    void numRecommendations900KNNEuc() throws SongNotInDataBaseException {
        recSysKNNEuclidean.run(tableTest, names);
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