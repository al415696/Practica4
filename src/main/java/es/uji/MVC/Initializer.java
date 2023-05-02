package es.uji.MVC;

import es.uji.MVC.Controlador.Controlador;
import es.uji.MVC.Modelo.InterrogaModelo;
import es.uji.MVC.Modelo.Modelo;
import es.uji.MVC.Vista.Vista;
import javafx.application.Application;
import javafx.stage.Stage;

public class Initializer extends Application  {
    InterrogaModelo model;

    public static void main(String[] args) {
        System.out.println("Prueba");
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Controlador controlador = new Controlador();
        Modelo modelo = new Modelo();
        Vista vista = new Vista(stage);
        modelo.setVista(vista);
        controlador.setVista(vista);
        controlador.setModelo(modelo);
        vista.setModelo(modelo);
        vista.setControlador(controlador);
        vista.creaGUI();
    }
}
