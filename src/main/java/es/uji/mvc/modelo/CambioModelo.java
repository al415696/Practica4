package es.uji.mvc.modelo;

import es.uji.recomendacion.SongNotInDataBaseException;

import java.util.List;

public interface CambioModelo {
    void selectAlgorithm(int indice);

    void selectDistance(int indice);

    List getListaRecomendaciones(String nameLikedItem, int numRecommendations) throws SongNotInDataBaseException;

    List updateListaRecomendaciones(String nameLikedItem, int numRecommendations) throws SongNotInDataBaseException;
}
