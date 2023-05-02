package es.uji.MVC.Modelo;

import es.uji.Exceptions.SongNotInDataBaseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface InterrogaModelo {
    void train() throws FileNotFoundException;
    ArrayList<String> getListaCanciones() throws FileNotFoundException;
    ArrayList<String> getListaRecomendaciones(String nameLikedItem, int numRecommendations, String estrategia, String algoritmo) throws IOException, SongNotInDataBaseException;

}
