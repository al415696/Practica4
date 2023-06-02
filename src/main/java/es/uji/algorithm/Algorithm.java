package es.uji.algorithm;

import es.uji.tables.Table;

public interface Algorithm<T extends Table, D, R> {
    void train(T datos);

    R estimate(D data);
}