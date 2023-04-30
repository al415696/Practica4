package es.uji.MVC.Vista;

import es.uji.CSV.CSV;
import es.uji.Estrategia.ManhatanDistance;
import es.uji.Exceptions.SongNotInDataBaseException;
import es.uji.KNN.KNN;
import es.uji.MVC.Controlador.Controlador;
import es.uji.Recomendacion.RecSys;
import es.uji.Tables.Table;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicReference;

public class Hello extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        primaryStage.setTitle("Song Recommender");
        StackPane root = new StackPane();


        ToggleGroup gRecommend = new ToggleGroup();
        Label titulo1 = new Label("Recommendation Type");
        RadioButton knn = new RadioButton("Recommend based on song features");
        knn.setOnAction(e -> System.out.println("Seleccionada opción knn."));
        RadioButton kmeans = new RadioButton("Recommend based on guessed genre");
        kmeans.setOnAction(e -> System.out.println("Seleccionada opción kmeans."));
        knn.setToggleGroup(gRecommend);
        kmeans.setToggleGroup(gRecommend);
        VBox vBoxKNNKmeans = new VBox(titulo1,kmeans,knn);

        ToggleGroup gDistance = new ToggleGroup();
        Label titulo2 = new Label("Distance Type");
        RadioButton euclidean = new RadioButton("Euclidean");
        euclidean.setOnAction(e -> System.out.println("Seleccionada opción euclidean."));
        RadioButton manhattan = new RadioButton("Manhattan");
        manhattan.setOnAction(e -> System.out.println("Seleccionada opción manhattan."));
        euclidean.setToggleGroup(gDistance);
        manhattan.setToggleGroup(gDistance);
        VBox vBoxEuMan = new VBox(titulo2,manhattan,euclidean);


        HBox hBOX = new HBox(vBoxKNNKmeans,vBoxEuMan);

        Label titulo3 = new Label("Song Titles");

        ObservableList<String> canciones = FXCollections.observableArrayList(Controlador.getListaCanciones());
        ListView<String> lista = new ListView<>(canciones);
        Label numRecomemnds = new Label("Number of Recommendations: ");
        Spinner<Double> numRecomendSpinner = new Spinner<>(0,50,0);

        Label ifYouLike = new Label();

        lista.setOnMouseClicked(value -> ifYouLike.setText("If you liked "+ lista.getSelectionModel().getSelectedItem() + " you might like"));


        ListView<String> listaRecomendaciones = new ListView<>();
        ObservableList<String> cancionesRecomendadas = FXCollections.observableArrayList("");
        numRecomendSpinner.setOnMouseClicked(value ->{
            try {
                cancionesRecomendadas.setAll(Controlador.getListaRecomendaciones(lista.getSelectionModel().getSelectedItem(),2));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SongNotInDataBaseException e) {
                throw new RuntimeException(e);
            }
        });
        listaRecomendaciones.setItems(cancionesRecomendadas);
        VBox total = new VBox(hBOX,titulo3,lista,numRecomemnds,numRecomendSpinner,ifYouLike,listaRecomendaciones);
        root.getChildren().add(total);
        primaryStage.setScene(new Scene(root, 350, 450));
        primaryStage.show();

    }
}
