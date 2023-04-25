package es.uji.Algorithm;

import es.uji.Tables.Table;

public interface Algorithm<T extends Table, D, R> {
    void train(T datos);

    R estimate(D data);
}
