package es.uji.MVC.Vista;

import es.uji.MVC.Controlador.Controller;
import es.uji.MVC.Modelo.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Vista implements InterrogaVista,InformaVista {
    private final Stage mainStage;
    private Controller controlador;
    InterrogaModelo modelo;


    public static void main(String[] args) {
        System.out.println("Prueba");
    }
    public void setModelo(final InterrogaModelo modelo) {
        this.modelo = modelo;
    }
    public Vista(final Stage stage) {
        this.mainStage = stage;
    }

    public void setControlador(final Controller controlador) {
        this.controlador = controlador;
    }

        public void creaGUI(){
        mainStage.setTitle("Song Recommender");
        StackPane root = new StackPane();

        //Options
        Label optionsLabel = new Label("Options");
        optionsLabel.setFont(Font.font(17));

        //num recomendaciones
        Label numRecomendLabel = new Label("Number of recomendations");
        numRecomendLabel.setFont(Font.font(13));
        Spinner<Double> numRecomendSpinner = new Spinner<>();
        HBox hboxNumRecomend = new HBox(numRecomendLabel,numRecomendSpinner);
        hboxNumRecomend.setSpacing(10);
        hboxNumRecomend.setAlignment(Pos.CENTER_LEFT);

        //Horizontal Options Box
            //Criteria
        Label basedLabel = new Label("Recommend based on:");
        basedLabel.setFont(Font.font(13));
        ToggleGroup basedOnGroup = new ToggleGroup();
        ToggleButton features = new ToggleButton("Song features");
        features.setToggleGroup(basedOnGroup);
        ToggleButton genre = new ToggleButton("Guessed genre");
        genre.setToggleGroup(basedOnGroup);
        VBox basedBox = new VBox(basedLabel, features,genre);
            //Distance
        Label distanceLabel = new Label("Recommend based on:");
        distanceLabel.setFont(Font.font(13));
        ToggleGroup distanceGroup = new ToggleGroup();
        ToggleButton manhathanButton = new ToggleButton("High precicion");
        manhathanButton.setToggleGroup(distanceGroup);
        ToggleButton euclideanButton = new ToggleButton("Standard precision");
        euclideanButton.setToggleGroup(distanceGroup);
        VBox distanceBox = new VBox(distanceLabel, manhathanButton,euclideanButton);
            //Options box
        HBox hboxOptions = new HBox(basedBox,distanceBox);
        hboxOptions.setSpacing(30);
        //Songs
        Label songsLabel = new Label("Song Titles");
        songsLabel.setFont(Font.font(17));
            //Song list
        ObservableList<String> songListNames = FXCollections.observableArrayList("Rightfully", "Peer Gynt", "Crazy My Beat", "Diggy Hole", "Bad Apple", "Star Platinum" +
                "Armor-clad Faith", "Nights Of Fire", "Deja Vu", "Rumbling", "The Day", "Flowering Night");
        ListView songList = new ListView<>(songListNames);
        //General
        Label uno = new Label("Uno");
        Label dos = new Label("Dos");
        Label tres = new Label("Tres");
        Label cuatro = new Label("Cuatro");
        VBox vBox = new VBox(optionsLabel,hboxNumRecomend,hboxOptions,songsLabel,songList,uno, dos, tres, cuatro);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.TOP_LEFT);
        root.getChildren().add(vBox);
        mainStage.setScene(new Scene(root, 400, 500));
        mainStage.show();


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
