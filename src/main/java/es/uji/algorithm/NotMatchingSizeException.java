package es.uji.algorithm;

public class NotMatchingSizeException extends Exception {
    public NotMatchingSizeException(String inClass) {
        super("in class " + inClass + "the size of the fields doesn't match");
    }
}
