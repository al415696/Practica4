package es.uji.Exceptions;


public class SongNotInDataBaseException extends Exception {
    public SongNotInDataBaseException(String message) {
        super(message);
    }
}
