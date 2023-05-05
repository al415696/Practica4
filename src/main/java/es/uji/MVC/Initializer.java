package es.uji.MVC;

import es.uji.MVC.Controlador.Controlador;
import es.uji.MVC.Modelo.InterrogaModelo;
import es.uji.MVC.Modelo.Modelo;
import es.uji.MVC.Vista.Vista;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Initializer extends Application  {
    InterrogaModelo model;

    public static void main(String[] args) {
        System.out.println("Prueba");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        Controlador controlador = new Controlador();
        Modelo modelo = new Modelo();
        Vista vista = new Vista(stage);

        modelo.setVista(vista);
        modelo.initializeSongNames("src/files/songs_test_names.csv");
        modelo.initializeTrainTable("src/files/songs_train_withoutnames.csv");
        modelo.initializeDataTable("src/files/songs_test_withoutnames.csv");


        controlador.setVista(vista);
        controlador.setModelo(modelo);

        vista.setModelo(modelo);
        vista.setControlador(controlador);
        vista.creaGUI();
    }
}
