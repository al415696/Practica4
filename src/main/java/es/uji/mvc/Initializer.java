package es.uji.mvc;

import es.uji.mvc.controlador.Controlador;
import es.uji.mvc.modelo.Modelo;
import es.uji.mvc.vista.Vista;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;

public class Initializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        Controlador controlador = new Controlador();
        Modelo modelo = new Modelo();
        Vista vista = new Vista(stage);

        modelo.setVista(vista);
        modelo.initializeSongNames("src" + File.separator + "files" + File.separator + "songs_test_names.csv");
        modelo.initializeTrainTable("src" + File.separator + "files" + File.separator + "songs_train.csv");
        modelo.initializeDataTable("src" + File.separator + "files" + File.separator + "songs_test.csv");


        controlador.setVista(vista);
        controlador.setModelo(modelo);

        vista.setModelo(modelo);
        vista.setControlador(controlador);
        vista.creaGUI();
    }
}
