package es.uji.MVC.Modelo;

import es.uji.Algorithm.Algorithm;
import es.uji.CSV.CSV;
import es.uji.Estrategia.Distance;
import es.uji.Estrategia.DistanceClient;
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

    private RecSys recomendador;
    private Algorithm algorithm ;
    //public<T implements Algorithm> algorithm;
    private Distance distance;

    private Table trainTable;
    private Table dataTable;
    private ArrayList<String> songNames;


    public void setVista(InformaVista vista) {
        this.vista = vista;
    }


    public void initializeTrainTable(String trainDirection) throws FileNotFoundException {
        trainTable = new CSV().readTableWithLabels(trainDirection);
    }
    public void initializeDataTable(String dataDirection) throws FileNotFoundException {
        dataTable = new CSV().readTableWithLabels(dataDirection);
    }
    public void initializeSongNames(String namesDirection) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(namesDirection));
        songNames = new ArrayList<>();
        while (scanner.hasNextLine()) songNames.add(scanner.nextLine());
    }
    @Override
    public void train(){
        recomendador = new RecSys(algorithm);
        recomendador.train(trainTable);
        recomendador.run(dataTable, songNames);
    }
    @Override
    public ArrayList<String> getListaCanciones() throws FileNotFoundException {
        return songNames;
    }

    @Override
    public List getListaRecomendaciones(String nameLikedItem, int numRecommendations) throws SongNotInDataBaseException {
       return recomendador.recommend(nameLikedItem,numRecommendations);
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
        //algorithm.    //setDistance(distance);

    }

    @Override
    public void cambiaAManhatan() {
        distance = new ManhatanDistance();
    }
}
