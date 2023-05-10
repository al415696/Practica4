package es.uji.exceptions;


public class SongNotInDataBaseException extends Exception {
    public SongNotInDataBaseException(String message) {
        super(message);
    }
}
