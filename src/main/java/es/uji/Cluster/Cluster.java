package es.uji.Cluster;

import es.uji.Rows.Row;

import java.util.ArrayList;
import java.util.List;

public class Cluster {
    int identificador;
    Row centroide;
    List<Row> grupo = new ArrayList<>();

    public Cluster(int identificador, Row centroide) {
        this.identificador = identificador;
        this.centroide = centroide;
    }

    public void addRow(Row nuevaFila) {
        grupo.add(nuevaFila);
    }


    public Row getCentroide() {
        return centroide;
    }

    public List<Row> getGrupo() {
        return grupo;
    }

    public void setCentroide(Row nuevoCentroide) {
        centroide = nuevoCentroide;
    }

    public void ClearGroup() {
        grupo = new ArrayList<Row>();
    }

    @Override
    public String toString() {
        return identificador + " Centroide = " + centroide.getData() + " " + grupo.size() + " miembros";
    }
}
