package es.uji.Recomendacion;

import es.uji.CSV.CSV;
import es.uji.Estrategia.ManhattanDistance;
import es.uji.Exceptions.SongNotInDataBaseException;
import es.uji.Algorithm.KNN.KNN;
import es.uji.Algorithm.Kmeans.Kmeans;
import es.uji.Tables.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class RecSysTest {
    RecSys recSysKmeans = new RecSys<>(new Kmeans(15, 10, 7777777, new ManhattanDistance()));
    RecSys recSysKNN = new RecSys<>(new KNN(new ManhattanDistance()));
    CSV csv = new CSV();
    Table tableTrain;
    Table tableTest;

    ArrayList<String> names;

    {
        try {
            tableTrain = csv.readTable("src/files/songs_train_withoutnames.csv");
            tableTest = csv.readTable("src/files/songs_test_withoutnames.csv");
            Scanner scanner = new Scanner(new File("src/files/songs_test_names.csv"));
            names = new ArrayList<>();
            while (scanner.hasNextLine()) names.add(scanner.nextLine());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    void setUp() {
        recSysKmeans.train(tableTrain);
    }

    @Test

    void recommendExactly10() throws SongNotInDataBaseException {
        recSysKmeans.run(tableTest,names);
        assertEquals(10, recSysKmeans.recommend("Tonight",10).size());
    }

    @Test

    void recommendsOfSameGroup() throws SongNotInDataBaseException {
        recSysKmeans.run(tableTest,names);
        List recomendaciones = recSysKmeans.recommend("Headbangers:Dubstep/Riddim",4);

        for (int i = 0; i < recomendaciones.size(); i++) {
            assertEquals(true, recSysKmeans.getSongGroup((String) recomendaciones.get(i)) == recSysKmeans.getSongGroup("Headbangers:Dubstep/Riddim"));
        }

    }

    @Test
    void numRecommendations900() throws SongNotInDataBaseException {
        recSysKmeans.run(tableTest, names);
        assertNotEquals(900, recSysKmeans.recommend("Tonight", 900).size());
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