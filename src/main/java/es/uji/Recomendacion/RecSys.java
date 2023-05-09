package es.uji.Recomendacion;

import es.uji.Algorithm.Algorithm;
import es.uji.Exceptions.SongNotInDataBaseException;
import es.uji.Tables.Table;

import java.util.ArrayList;
import java.util.List;

public class RecSys<D, R> {
    Algorithm algorithm;
    List<Integer> grupoTestData = new ArrayList<>();
    List<String> testItemNames;

    public RecSys(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public void train(Table trainData) {
        algorithm.train(trainData);
    }

    public void run(Table testData, List<String> testItemNames) {

        for (int i = 1; i < testData.getSize(); i++) {
            grupoTestData.add( (Integer)algorithm.estimate(testData.getRowAt(i).getData()));
        }
        ArrayList<String> respuesta = new ArrayList<>();
        respuesta.addAll(testItemNames);
        this.testItemNames = respuesta;
    }


    public List<String> recommend(String nameLikedItem, int numRecommendations) throws SongNotInDataBaseException {
        try{
        int indexName = findName(nameLikedItem);
        int grupo = grupoTestData.get(indexName);
        return recomendOfSameGroup(grupo, nameLikedItem, numRecommendations);
    }
        catch ( SongNotInDataBaseException e){
            e.printStackTrace();
            return new ArrayList<String>();
        }
    }

    private Integer findName(String nameOfItem) throws SongNotInDataBaseException{

        int i = 0;
        while(i < testItemNames.size()) {
            if(testItemNames.get(i).equals(nameOfItem)) {
                return i;
            }
            i++;
        }
        throw new SongNotInDataBaseException("No encontrada");


    }

    private List<String> recomendOfSameGroup(int grupo, String nombre, int numeroRec) {
        ArrayList<String> recomendacion = new ArrayList<>();
        int contador = 0;

        for (int i = 0; i < grupoTestData.size(); i++) {

            if (grupoTestData.get(i) == grupo && !testItemNames.get(i).equals(nombre)) {
                recomendacion.add(testItemNames.get(i));

                contador++;
                if (contador == numeroRec)
                    return recomendacion;
            }

        }
        return recomendacion;
    }

    public int getSongGroup(String songName) {
        try {
            return grupoTestData.get(findName(songName));
        }
        catch (SongNotInDataBaseException e){
            e.printStackTrace();
            return -1;
        }
    }
}
