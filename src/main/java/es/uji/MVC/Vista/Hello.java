package es.uji.MVC.Vista;

import javafx.application.Application;
import javafx.geometry.Pos;
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

        //num recomendaciones
        Label numRecomendLabel = new Label("Number of recomendations");
        Spinner<Double> numRecomendSpinner = new Spinner<>();
        HBox hboxNumRecomend = new HBox(numRecomendLabel,numRecomendSpinner);
        hboxNumRecomend.setSpacing(10);
        hboxNumRecomend.setAlignment(Pos.CENTER_LEFT);

        //Options
            //Criteria
        Label based = new Label("Recommend based on:");
        ToggleGroup basedOnGroup = new ToggleGroup();
        ToggleButton features = new ToggleButton("Song features");
        features.setToggleGroup(basedOnGroup);
        ToggleButton genre = new ToggleButton("Guessed genre");
        genre.setToggleGroup(basedOnGroup);
        VBox gessedBox = new VBox(based, features,genre);
            //Distance


        HBox hboxOptions = new HBox(gessedBox);
        //General
        Label uno = new Label("Uno");
        Label dos = new Label("Dos");
        Label tres = new Label("Tres");
        Label cuatro = new Label("Cuatro");
        VBox vBox = new VBox(hboxNumRecomend,hboxOptions, dos, tres, cuatro);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.TOP_LEFT);
        root.getChildren().add(vBox);
        primaryStage.setScene(new Scene(root, 250, 250));
        primaryStage.show();


        //https://www.youtube.com/watch?v=VUVqamT8Npc


        /*ToggleGroup gRecommend = new ToggleGroup();
        RadioButton knn = new RadioButton("Recommend based on song features");
        knn.setOnAction(e -> System.out.println("Seleccionada opción knn."));
        RadioButton kmeans = new RadioButton("Recommend based on guessed genre");
        kmeans.setOnAction(e -> System.out.println("Seleccionada opción kmeans."));
        knn.setToggleGroup(gRecommend);
        kmeans.setToggleGroup(gRecommend);
        root.getChildren().add(kmeans);
        root.getChildren().add(knn);

         */

    }
}
