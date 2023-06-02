package es.uji.recomendacion;


public class SongNotInDataBaseException extends Exception {
    public SongNotInDataBaseException(String message) {
        super(message);
    }
}
