package es.uji.MVC.Modelo;

import javafx.scene.control.ListView;

public interface InterrogaModelo {
    int getNumeroEntradas();
    String getEntradaActual();
    int getPoscionEntradaActual();
    ListView<String> getListaActual();
}
