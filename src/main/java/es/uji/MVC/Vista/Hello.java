package es.uji.MVC.Vista;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Hello extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
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
        ObservableList<String> meses = FXCollections.observableArrayList("Enero", "Febrero", "Marzo",
                "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre",
                "Noviembre", "Diciembre");
        ListView<String> lista = new ListView<>(meses);

        VBox total = new VBox(hBOX,titulo3,lista);
        root.getChildren().add(total);
        primaryStage.setScene(new Scene(root, 350, 250));
        primaryStage.show();
    }
}
