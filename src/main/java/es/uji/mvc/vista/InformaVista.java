package es.uji.mvc.vista;

import java.util.List;

public interface InformaVista {
    void createGenericPopUp(String title, String body);

    void createRecomendationPopUp(String songTitle, List<String> recomendations);
}
