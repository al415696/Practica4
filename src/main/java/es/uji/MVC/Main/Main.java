package es.uji.MVC.Main;

import es.uji.MVC.Controlador.ImplementacionControlador;
import es.uji.MVC.Modelo.ImplementacionModelo;
import es.uji.MVC.Vista.ImplementacionVista;
import javafx.application.Application;
import javafx.stage.Stage;


import java.io.FileNotFoundException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        ImplementacionControlador controlador = new ImplementacionControlador();
        ImplementacionModelo modelo = new ImplementacionModelo();
        ImplementacionVista vista = new ImplementacionVista(stage);
        modelo.setVista(vista);
        controlador.setVista(vista);
        controlador.setModelo(modelo);
        vista.setModelo(modelo);
        vista.setControlador(controlador);
        vista.creaGUI();
    }

}
