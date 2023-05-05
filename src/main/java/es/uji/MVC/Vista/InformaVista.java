package es.uji.MVC.Vista;

import java.util.ArrayList;
import java.util.List;

public interface InformaVista {
    void createGenericPopUp(String title, String body);

    void createRecomendationPopUp(String songTitle, List<String> recomendations);
}
