package es.uji.MVC.Modelo;

import es.uji.Exceptions.SongNotInDataBaseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface InterrogaModelo {
    ArrayList<String> getListaCanciones() throws FileNotFoundException;
    List getListaRecomendaciones(String nameLikedItem, int numRecommendations) throws SongNotInDataBaseException;

}
