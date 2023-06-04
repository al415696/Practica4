package es.uji.mvc.controlador;

import es.uji.mvc.modelo.CambioModelo;
import es.uji.mvc.vista.InterrogaVista;
import es.uji.recomendacion.SongNotInDataBaseException;
import java.util.List;


public class Controlador implements Controller {
    private CambioModelo modelo;
    private InterrogaVista vista;

    public void setModelo(CambioModelo modelo) {
        this.modelo = modelo;
    }

    public void setVista(InterrogaVista vista) {
        this.vista = vista;

    }
    public List getListaRecomendacionesControlador(String nameLikedItem, int numRecommendations) throws SongNotInDataBaseException {
        return modelo.getListaRecomendaciones(nameLikedItem,numRecommendations);
    }
    @Override
    public void selectAlgorithm(int indice) {
        modelo.selectAlgorithm(indice);
    }

    @Override
    public void selectDistance(int indice) {
        modelo.selectDistance(indice);
    }
}