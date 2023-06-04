package es.uji.mvc.controlador;

import es.uji.mvc.modelo.CambioModelo;
import es.uji.mvc.vista.InterrogaVista;
import es.uji.recomendacion.SongNotInDataBaseException;

import java.util.List;


public class Controlador implements Controller {
    private CambioModelo modelo;
    private InterrogaVista vista;
    private boolean unalteredRecomendator = false;

    public void setModelo(CambioModelo modelo) {
        this.modelo = modelo;
    }

    public void setVista(InterrogaVista vista) {
        this.vista = vista;

    }

    public List getListaRecomendacionesControlador(String nameLikedItem, int numRecommendations) throws SongNotInDataBaseException {
        if (unalteredRecomendator) {
            return modelo.updateListaRecomendaciones(nameLikedItem, numRecommendations);
        } else {
            unalteredRecomendator = true;
            return modelo.getListaRecomendaciones(nameLikedItem, numRecommendations);
        }

    }

    @Override
    public void selectAlgorithm(int indice) {
        unalteredRecomendator = false;
        modelo.selectAlgorithm(indice);
    }

    @Override
    public void selectDistance(int indice) {
        unalteredRecomendator = false;
        modelo.selectDistance(indice);
    }
}