package es.uji.MVC.Vista;

import es.uji.Exceptions.SongNotInDataBaseException;
import es.uji.MVC.Controlador.Controller;
import es.uji.MVC.Modelo.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Vista implements InterrogaVista,InformaVista {
    private final Stage mainStage;
    private Controller controlador;
    InterrogaModelo modelo;

    private ToggleButton features;
    private ToggleButton genre;

    private ToggleButton manhattanButton;
    private ToggleButton euclideanButton;


    private Spinner<Integer> numRecomendSpinner;
    private ListView<String> lista;

    private Button recomend;

    private StringBuilder body;
    private Label recomendationPopUpBodyLabel;

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
        root.setPadding(new Insets(10));

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
        features = new ToggleButton("Song features");
        features.setTooltip(new Tooltip("Recomend based on features by using the Kmeans algorithm"));
        features.setOnAction(e -> {
            controlador.selectAlgorithm(0);
            setIfReady();
        });
        features.setToggleGroup(basedOnGroup);
        genre = new ToggleButton("Guessed genre");
        genre.setTooltip(new Tooltip("Recomend based on genre by using the KNN algorithm"));
        genre.setOnAction(e -> {
            controlador.selectAlgorithm(1);
            setIfReady();
        });
        genre.setToggleGroup(basedOnGroup);
        VBox basedBox = new VBox(basedLabel, features,genre);
            //Distance
        Label distanceLabel = new Label("Recommend using:");
        distanceLabel.setFont(Font.font(13));
        ToggleGroup distanceGroup = new ToggleGroup();
         manhattanButton = new ToggleButton("High precicion");
         manhattanButton.setTooltip(new Tooltip("Use manhattan distance"));
        manhattanButton.setOnAction(e-> {
            controlador.selectDistance(0);
            setIfReady();
        });
        manhattanButton.setToggleGroup(distanceGroup);
         euclideanButton = new ToggleButton("Standard precision");
         euclideanButton.setTooltip(new Tooltip("Use euclidean distance"));
        euclideanButton.setToggleGroup(distanceGroup);
        euclideanButton.setOnAction(e-> {
            controlador.selectDistance(1);
            setIfReady();
        });
        VBox distanceBox = new VBox(distanceLabel, manhattanButton,euclideanButton);
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

            lista.setOnMouseClicked(e-> {
                setIfReady();
                if(e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2 && !recomend.isDisabled())
                try {
                    createRecomendationPopUp(modelo.getListaCanciones().get(lista.getSelectionModel().getSelectedIndex()),
                            modelo.getListaRecomendaciones(modelo.getListaCanciones().get(lista.getSelectionModel().getSelectedIndex()),numRecomendSpinner.getValue()));
                    modelo.getListaRecomendaciones(modelo.getListaCanciones().get(lista.getSelectionModel().getSelectedIndex()),numRecomendSpinner.getValue());

                } catch (SongNotInDataBaseException ex) {
                    System.out.println("Canción no encontrada");
                    createGenericPopUp("SONG NOT FOUND", "An unexpected error has ocurred and the song you selected can't be found on the database." +
                            "\nTry again with other song or contact your low quality applications provider");

                }
            });

        //Recomend

            recomend = new Button("recommend");
            recomend.setDisable(true);
            //recomend.setTooltip(new Tooltip("Select criteria, method and song before trying to get a recomendation"));
            SplitPane recomendPane = new SplitPane(recomend);
            recomendPane.setTooltip(new Tooltip("Select criteria, method and song before trying to get a recomendation"));
            //ACABAR ESTO!!!
            recomend.setOnAction(e-> {
                try {
                    System.out.println("IndiceCancion "+modelo.getListaCanciones().get(lista.getSelectionModel().getSelectedIndex()));
                    System.out.println("NumSpinner "+numRecomendSpinner.getValue());
                    createRecomendationPopUp(modelo.getListaCanciones().get(lista.getSelectionModel().getSelectedIndex()),
                            modelo.getListaRecomendaciones(modelo.getListaCanciones().get(lista.getSelectionModel().getSelectedIndex()),numRecomendSpinner.getValue()));
                    modelo.getListaRecomendaciones(modelo.getListaCanciones().get(lista.getSelectionModel().getSelectedIndex()),numRecomendSpinner.getValue());
                } catch (SongNotInDataBaseException ex) {
                    System.out.println("Canción no encontrada");
                    createGenericPopUp("SONG NOT FOUND", "An unexpected error has ocurred and the song you selected can't be found on the database." +
                            "\nTry again with other song or contact your low quality applications provider");
                }
            });
        //General
            /*
        Label uno = new Label("Uno");
        Label dos = new Label("Dos");
        Label tres = new Label("Tres");
        Label cuatro = new Label("Cuatro");
        */
        VBox vBox = new VBox(optionsLabel,hboxNumRecomend,hboxOptions,songsLabel,lista,recomendPane);

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
    public void createGenericPopUp(String title, String body){
        System.out.println("Create popUP");
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
    @Override
    public void createRecomendationPopUp(String songTitle, List<String> recomendations){
        System.out.println("Create recomendPopUP");
        Stage popUpStage = new Stage();

        Label newNumRecomendLabel = new Label("Number of recomendations");
        newNumRecomendLabel.setFont(Font.font(13));
        Spinner<Integer> newNumRecomendSpinner = new Spinner<>(1,50,1);
        newNumRecomendSpinner.getValueFactory().setValue(numRecomendSpinner.getValue());
        newNumRecomendSpinner.setOnMouseClicked(e->{

            List<String> reRecomendationList = new ArrayList();
            try {
                reRecomendationList = modelo.getListaRecomendaciones(modelo.getListaCanciones().get(lista.getSelectionModel().getSelectedIndex()),newNumRecomendSpinner.getValue());
            } catch (SongNotInDataBaseException ex) {
                createGenericPopUp("UNEXPECTED ERROR", "An unexpected error has ocurred and the updated number of recomendations cannot be shown");
            }
            body = new StringBuilder();
            for (String recom : reRecomendationList)
                body.append(recom).append("\n");
            recomendationPopUpBodyLabel.setText(body.toString());


        });
        Label titleLabel = new Label("Recomendations for " + songTitle+ " :");
        titleLabel.setFont(Font.font(17));
         body = new StringBuilder();
        for (String recom : recomendations)
            body.append(recom).append("\n");
        recomendationPopUpBodyLabel = new Label(body.toString());
        recomendationPopUpBodyLabel.setFont(Font.font(13));

        ScrollPane scrollPane = new ScrollPane(recomendationPopUpBodyLabel);
        scrollPane.setHmin(100);
        VBox vBox = new VBox(newNumRecomendLabel,newNumRecomendSpinner, titleLabel,scrollPane);

        FlowPane recomendationPopUpPane = new FlowPane(vBox);
        recomendationPopUpPane.setPadding(new Insets(5,10,10,10));
        //recomendationPopUpPane.setHmin(400);
        //recomendationPopUpPane.setVmin(400);
        Scene popUpScene = new Scene(recomendationPopUpPane);

        popUpStage.setScene(popUpScene);
        popUpStage.show();
        popUpStage.setAlwaysOnTop(true);
    }

    /*
    public int getNumRecomendations() {
        return numRecomendSpinner.getValue();
    }

     */

    private void setIfReady(){
        if ((features.isSelected() || genre.isSelected()) && (manhattanButton.isSelected() || euclideanButton.isSelected()) && lista.getSelectionModel().getSelectedIndex()>=0 ){
            recomend.setDisable(false);
        }
        else{
            recomend.setDisable(true);
        }
    }
}
