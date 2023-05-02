package es.uji.MVC.Modelo;

import javafx.scene.control.ListView;

import javax.swing.*;

public interface CambioModelo {
    void anyadeEntrada(String entrada);
    void incrementaPosicionActual();
    void decrementaPosicionActual();
    ListView<String> getListaActual();
}
