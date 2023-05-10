package es.uji.mvc.modelo;

import es.uji.exceptions.SongNotInDataBaseException;

import java.util.ArrayList;
import java.util.List;

public interface InterrogaModelo {
    ArrayList<String> getListaCanciones();
    List getListaRecomendaciones(String nameLikedItem, int numRecommendations) throws SongNotInDataBaseException;

}
