package es.uji.MVC.Vista;

import es.uji.Exceptions.SongNotInDataBaseException;
import es.uji.MVC.Controlador.Controlador;
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
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class Hello extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        primaryStage.setTitle("Song Recommender");
        StackPane root = new StackPane();
        AtomicReference<String> estrategia = new AtomicReference<>();
        AtomicReference<String> algoritmo = new AtomicReference<>();

        ToggleGroup gRecommend = new ToggleGroup();
        Label titulo1 = new Label("Recommendation Type");
        RadioButton knn = new RadioButton("Recommend based on song features");
        knn.setOnAction(e -> algoritmo.set("knn"));
        RadioButton kmeans = new RadioButton("Recommend based on guessed genre");
        kmeans.setOnAction(e -> algoritmo.set("kmeans"));
        knn.setToggleGroup(gRecommend);
        kmeans.setToggleGroup(gRecommend);
        VBox vBoxKNNKmeans = new VBox(titulo1,kmeans,knn);

        ToggleGroup gDistance = new ToggleGroup();
        Label titulo2 = new Label("Distance Type");
        RadioButton euclidean = new RadioButton("Euclidean");
        euclidean.setOnAction(e -> estrategia.set("Euclidean"));
        RadioButton manhattan = new RadioButton("Manhattan");
        manhattan.setOnAction(e -> estrategia.set("Manhattan"));
        euclidean.setToggleGroup(gDistance);
        manhattan.setToggleGroup(gDistance);
        VBox vBoxEuMan = new VBox(titulo2,manhattan,euclidean);


        HBox hBOX = new HBox(vBoxKNNKmeans,vBoxEuMan);

        Label titulo3 = new Label("Song Titles");

        ObservableList<String> canciones = FXCollections.observableArrayList(Controlador.getListaCanciones());
        ListView<String> lista = new ListView<>(canciones);
        Label numRecomemnds = new Label("Number of Recommendations: ");
        Spinner<Integer> numRecomendSpinner = new Spinner<>(0,50,0);

        Label ifYouLike = new Label();

        lista.setOnMouseClicked(value -> ifYouLike.setText("If you liked "+ lista.getSelectionModel().getSelectedItem() + " you might like"));
        Button botonRecomend = new Button("Recomendar");
        HBox hBoxSpinnerBoton = new HBox(numRecomendSpinner,botonRecomend);

        ListView<String> listaRecomendaciones = new ListView<>();
        ObservableList<String> cancionesRecomendadas = FXCollections.observableArrayList("");
        botonRecomend.setOnAction(value ->{
            try {
                String nameLikedItem = lista.getSelectionModel().getSelectedItem();
                int numRecommendations = numRecomendSpinner.getValueFactory().getValue();
                cancionesRecomendadas.setAll(Controlador.getListaRecomendaciones(nameLikedItem, numRecommendations, estrategia.get(), algoritmo.get()));

            } catch (SongNotInDataBaseException | IOException e) {
                throw new RuntimeException(e);
            }
        });
        listaRecomendaciones.setItems(cancionesRecomendadas);
        VBox total = new VBox(hBOX,titulo3,lista,numRecomemnds,hBoxSpinnerBoton,ifYouLike,listaRecomendaciones);
        root.getChildren().add(total);
        primaryStage.setScene(new Scene(root, 350, 450));
        primaryStage.show();

    }
}
