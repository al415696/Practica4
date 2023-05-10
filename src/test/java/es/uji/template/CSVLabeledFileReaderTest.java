package es.uji.template;

import es.uji.csv.CSV;
import es.uji.tables.TableWithLabels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CSVLabeledFileReaderTest {

    TableWithLabels primaryTable;
    TableWithLabels secondaryTable;
    @BeforeEach
    void setUp() {

    }

    @Test
    void TestReadTableFromSoure(){
        CSV csv = new CSV();

        //assertEquals();
    }
}
