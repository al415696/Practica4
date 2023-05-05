package es.uji.MVC.Vista;

import es.uji.Exceptions.SongNotInDataBaseException;
import es.uji.MVC.Controlador.Controlador;
import es.uji.MVC.Controlador.Controller;
import es.uji.MVC.Modelo.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Vista implements InterrogaVista,InformaVista {
    private final Stage mainStage;
    private Controller controlador;
    InterrogaModelo modelo;

    private Spinner<Integer> numRecomendSpinner;
    private ListView<String> lista;


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

        public void creaGUI() throws FileNotFoundException {
        mainStage.setTitle("Song Recommender");
        StackPane root = new StackPane();

        //Options
        Label optionsLabel = new Label("Options");
        optionsLabel.setFont(Font.font(17));

        //num recomendaciones
        Label numRecomendLabel = new Label("Number of recomendations");
        numRecomendLabel.setFont(Font.font(13));
        numRecomendSpinner = new Spinner<>(1,50,1);
        HBox hboxNumRecomend = new HBox(numRecomendLabel,numRecomendSpinner);
        hboxNumRecomend.setSpacing(10);
        hboxNumRecomend.setAlignment(Pos.CENTER_LEFT);

        //Horizontal Options Box
            //Criteria
        Label basedLabel = new Label("Recommend based on:");
        basedLabel.setFont(Font.font(13));
        ToggleGroup basedOnGroup = new ToggleGroup();
        ToggleButton features = new ToggleButton("Song features");
        features.setOnAction(e -> controlador.selectAlgorithm(0));
        features.setToggleGroup(basedOnGroup);
        ToggleButton genre = new ToggleButton("Guessed genre");
        genre.setOnAction(e -> controlador.selectAlgorithm(1));
        genre.setToggleGroup(basedOnGroup);
        VBox basedBox = new VBox(basedLabel, features,genre);
            //Distance
        Label distanceLabel = new Label("Recommend based on:");
        distanceLabel.setFont(Font.font(13));
        ToggleGroup distanceGroup = new ToggleGroup();
        ToggleButton manhathanButton = new ToggleButton("High precicion");
        manhathanButton.setOnAction(e-> controlador.selectDistance(0));
        manhathanButton.setToggleGroup(distanceGroup);
        ToggleButton euclideanButton = new ToggleButton("Standard precision");
        euclideanButton.setToggleGroup(distanceGroup);
        euclideanButton.setOnAction(e-> controlador.selectDistance(1));
        VBox distanceBox = new VBox(distanceLabel, manhathanButton,euclideanButton);
            //Options box
        HBox hboxOptions = new HBox(basedBox,distanceBox);
        hboxOptions.setSpacing(30);
        //Songs
        Label songsLabel = new Label("Song Titles");
        songsLabel.setFont(Font.font(17));
            //Song list
            ObservableList<String> songList = FXCollections.observableArrayList(modelo.getListaCanciones());
            lista = new ListView<>(songList);
            lista.setTooltip(new Tooltip("Select song to recommend similar to"));
            /*
            lista.setOnMouseClicked(e-> {
                try {
                    modelo.getListaRecomendaciones(modelo.getListaCanciones().get(lista.getSelectionModel().getSelectedIndex()),numRecomendSpinner.getValue());
                } catch (SongNotInDataBaseException ex) {
                    System.out.println("Canción no encontrada");
                    createPopUp("SONG NOT FOUND", "An error has ocurred and the song you selected can't be found, try again");

                }
            });
             */
        //General
            Button recomend = new Button("recommend");
            //ACABAR ESTO!!!
            //ACABAR ESTO!!!
            //ACABAR ESTO!!!
            //ACABAR ESTO!!!
            recomend.setOnAction(e-> {
                try {
                    modelo.getListaRecomendaciones(modelo.getListaCanciones().get(lista.getSelectionModel().getSelectedIndex()),numRecomendSpinner.getValue());
                } catch (SongNotInDataBaseException ex) {
                    System.out.println("Canción no encontrada");
                    createPopUp("SONG NOT FOUND", "An error has ocurred and the song you selected can't be found, try again");
                }
            });

        Label uno = new Label("Uno");
        Label dos = new Label("Dos");
        Label tres = new Label("Tres");
        Label cuatro = new Label("Cuatro");

        VBox vBox = new VBox(optionsLabel,hboxNumRecomend,hboxOptions,songsLabel,lista,recomend,uno, dos, tres, cuatro);

        vBox.setSpacing(10); vBox.setAlignment(Pos.TOP_LEFT); root.getChildren().add(vBox);
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
    @Override
    public void createPopUp(String title, String body){
        Stage popUpStage = new Stage();


        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font(17));
        Label bodyLabel = new Label(body);
        bodyLabel.setFont(Font.font(10));
        VBox vBox = new VBox(titleLabel,bodyLabel);
        FlowPane flowPane = new FlowPane(vBox);
        Scene popUpScene = new Scene(flowPane);
        popUpStage.setScene(popUpScene);
        popUpStage.show();
    }
    /*
    public int getNumRecomendations() {
        return numRecomendSpinner.getValue();
    }

     */

}
