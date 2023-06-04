package es.uji.recomendacion;

import es.uji.algorithm.IncompatiblePositionFormatException;
import es.uji.algorithm.knn.KNN;
import es.uji.algorithm.kmeans.Kmeans;
import es.uji.csv.CSV;
import es.uji.estrategia.EuclideanDistance;
import es.uji.estrategia.ManhattanDistance;
import es.uji.tables.Table;
import es.uji.tables.TableWithLabels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class RecSysTest {
    RecSys recSysKmeansManhattan = new RecSys<>(new Kmeans(15, 10, 7777777, new ManhattanDistance()));
    RecSys recSysKNNManhattan = new RecSys<>(new KNN(new ManhattanDistance()));
    RecSys recSysKmeansEuclidean = new RecSys<>(new Kmeans(15, 10, 7777777, new EuclideanDistance()));
    RecSys recSysKNNEuclidean = new RecSys<>(new KNN(new EuclideanDistance()));
    CSV csv = new CSV();
    Table tableTrain = csv.readTable("src"+ File.separator +"files"+ File.separator +"songs_train_withoutnames.csv");
    TableWithLabels  tableWithLabelsTrain = csv.readTableWithLabels("src"+ File.separator +"files"+ File.separator +"songs_train.csv");
    Table tableTest = csv.readTable("src"+ File.separator +"files"+ File.separator +"songs_test_withoutnames.csv");
    ArrayList<String> names = new ArrayList<>();
    
    RecSysTest() throws FileNotFoundException {
        super();
        Scanner scanner = new Scanner(new File("src"+ File.separator +"files"+ File.separator +"songs_test_names.csv"));
        while (scanner.hasNextLine()) names.add(scanner.nextLine());
    }




    @BeforeEach
    void setUp() throws IncompatiblePositionFormatException {
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
    void numRecommendations900KmeansMan() throws SongNotInDataBaseException {
        assertNotEquals(900, recSysKmeansManhattan.recommend("Tonight", 900).size());
    }
    @Test
    void recommendExactly10KNNMan() throws SongNotInDataBaseException {
        System.out.println("3");
        assertEquals(10, recSysKNNManhattan.recommend("Tonight",10).size());
    }

    @Test
    void numRecommendations900KNNMan() throws SongNotInDataBaseException {
        System.out.println("5");
        assertNotEquals(900, recSysKNNManhattan.recommend("Tonight", 900).size());
    }
    @Test
    void recommendExactly10KMeansEuc() throws SongNotInDataBaseException {
        assertEquals(10, recSysKmeansEuclidean.recommend("Tonight",10).size());
    }

    @Test
    void numRecommendations900KMeansEuc() throws SongNotInDataBaseException {
        assertNotEquals(900, recSysKmeansEuclidean.recommend("Tonight", 900).size());
    }

    @Test
    void recommendExactly10KNNEuc() throws SongNotInDataBaseException {
        System.out.println("6");
        System.out.println(recSysKNNEuclidean.recommend("Tonight",10));
        assertEquals(10, recSysKNNEuclidean.recommend("Tonight",10).size());
    }

    @Test
    void numRecommendations900KNNEuc() throws SongNotInDataBaseException {
        assertNotEquals(900, recSysKNNEuclidean.recommend("Tonight", 900).size());
    }
}