package es.uji.MVC.Modelo;

import es.uji.Algorithm.Algorithm;
import es.uji.Algorithm.KNN.KNN;
import es.uji.Algorithm.Kmeans.Kmeans;
import es.uji.CSV.CSV;
import es.uji.Estrategia.Distance;
import es.uji.Estrategia.EuclideanDistance;
import es.uji.Estrategia.ManhattanDistance;
import es.uji.Exceptions.SongNotInDataBaseException;
import es.uji.MVC.Vista.InformaVista;
import es.uji.Recomendacion.RecSys;
import es.uji.Tables.TableWithLabels;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Modelo implements InterrogaModelo,CambioModelo {

    private InformaVista vista;

    private RecSys recomendador;

    //public<T implements Algorithm> algorithm;


    private enum algoritmoUsado {
        Kmeans,
        KNN;
    }
    private algoritmoUsado algoritmoActual;
    private enum distanciaUsada {
        Manhattan,
        Euclidean;
    }
    private distanciaUsada distanciaActual;

    //Tablas internas:
    private TableWithLabels trainTable;
    private TableWithLabels dataTable;
    private ArrayList<String> songNames = new ArrayList<>();

    private int numOfGenres=0;

    public void setVista(InformaVista vista) {
        this.vista = vista;
    }


    public void initializeTrainTable(String trainDirection) throws FileNotFoundException {
        trainTable = new CSV().readTableWithLabels(trainDirection);
    }
    public void initializeDataTable(String dataDirection) throws FileNotFoundException {
        dataTable = new CSV().readTableWithLabels(dataDirection);
        numOfGenres = dataTable.getNumOfLabels();
    }
    public void initializeSongNames(String namesDirection) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(namesDirection));
        songNames = new ArrayList<>();
        while (scanner.hasNextLine()) songNames.add(scanner.nextLine());
    }

    private void train(Algorithm algorithm){
        recomendador = new RecSys(algorithm);
        recomendador.train(trainTable);
        recomendador.run(dataTable, songNames);
    }
    @Override
    public ArrayList<String> getListaCanciones(){
        return songNames;
    }

    @Override
    public List getListaRecomendaciones(String nameLikedItem,int numRecommendations ) throws SongNotInDataBaseException {
        Distance distance;
        switch (distanciaActual) {
            case Manhattan -> {
                distance = new ManhattanDistance();
            }
            case Euclidean -> {
                distance = new EuclideanDistance();
            }
            default -> {
                System.out.printf("Distancia desconocida");
                vista.createGenericPopUp("DISTANCE NOT SELECTED", "Distance was not selected please, select it");
                return new ArrayList<>();
            }
        }

        Algorithm algorithm ;
        switch (algoritmoActual){
            case Kmeans -> {
                algorithm = new Kmeans(numOfGenres,10,7777777,distance);
            }
            case KNN -> {
                algorithm = new KNN(distance);
            }
            default -> {
                System.out.printf("Algoritmo desconocido");
                algorithm = new KNN(distance);
                vista.createGenericPopUp("ALGORITHM NOT SELECTED", "Algorithm was not selected please, select it");
                return new ArrayList<>();
            }
        }

        train(algorithm);
        System.out.println("num recomend " + numRecommendations);
        System.out.println("name song " + nameLikedItem);
        System.out.println("recomendación es: "+ recomendador.recommend(nameLikedItem,numRecommendations));
        //vista.createGenericPopUp("PRUEBA", "Prueba");
       return recomendador.recommend(nameLikedItem,numRecommendations);
    }
    @Override
    public void selectAlgorithm(int indice) {
        algoritmoActual =  algoritmoUsado.values()[indice];
        System.out.println("ahora algoritmos es " + algoritmoUsado.values()[indice]);
    }


       // algorithm = new Kmeans(15,10,7777777,distance);

    @Override
    public void selectDistance(int indice) {
        distanciaActual =  distanciaUsada.values()[indice];
        System.out.println("ahora distancia es " + distanciaUsada.values()[indice]);
    }

}
