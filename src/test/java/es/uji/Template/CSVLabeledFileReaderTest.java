package es.uji.Template;

import es.uji.CSV.CSV;
import es.uji.Tables.TableWithLabels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
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
