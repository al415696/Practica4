package es.uji.mvc;

import es.uji.mvc.controlador.Controlador;
import es.uji.mvc.modelo.InterrogaModelo;
import es.uji.mvc.modelo.Modelo;
import es.uji.mvc.vista.Vista;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Initializer extends Application  {
    InterrogaModelo model;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        Controlador controlador = new Controlador();
        Modelo modelo = new Modelo();
        Vista vista = new Vista(stage);

        modelo.setVista(vista);
        modelo.initializeSongNames("src/files/songs_test_names.csv");
        modelo.initializeTrainTable("src/files/songs_train.csv");
        modelo.initializeDataTable("src/files/songs_test.csv");


        controlador.setVista(vista);
        controlador.setModelo(modelo);

        vista.setModelo(modelo);
        vista.setControlador(controlador);
        vista.creaGUI();
    }
}
