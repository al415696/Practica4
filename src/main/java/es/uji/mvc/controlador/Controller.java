package es.uji.mvc.controlador;

import es.uji.recomendacion.SongNotInDataBaseException;

import java.util.List;

public interface Controller {
    void selectAlgorithm(int indice);

    void selectDistance(int indice);

    List<String> getListaRecomendacionesControlador(String nameLikedItem, int numRecommendations) throws SongNotInDataBaseException;
}
