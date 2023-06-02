package es.uji.algorithm;

import es.uji.tables.Table;

public interface Algorithm<T extends Table, D, R> {
    void train(T datos) throws IncompatiblePositionFormatException;

    R estimate(D data) throws NotMatchingSizeException, IncompatiblePositionFormatException;
}
