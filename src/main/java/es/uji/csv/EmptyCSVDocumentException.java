package es.uji.csv;

public class EmptyCSVDocumentException extends Exception{
    public EmptyCSVDocumentException(String source){
        super("The document " + source + " was empty and couldn't be used");
    }
}
