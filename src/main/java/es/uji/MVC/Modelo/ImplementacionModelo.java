package es.uji.MVC.Modelo;

import es.uji.MVC.Vista.InformaVista;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class ImplementacionModelo implements CambioModelo, InterrogaModelo{
    private ArrayList<String> entradas = new ArrayList<String>();
    private int posicionActual = 0;
    private InformaVista vista;

    public ImplementacionModelo(){}

    public void setVista(InformaVista vista) {
        this.vista = vista;
    }
    @Override
    public void anyadeEntrada(String entrada) {

    }

    @Override
    public void incrementaPosicionActual() {

    }

    @Override
    public void decrementaPosicionActual() {

    }

    @Override
    public ListView<String> getListaActual() {
        return null;
    }

    @Override
    public int getNumeroEntradas() {
        return 0;
    }

    @Override
    public String getEntradaActual() {
        return null;
    }

    @Override
    public int getPoscionEntradaActual() {
        return 0;
    }

}
