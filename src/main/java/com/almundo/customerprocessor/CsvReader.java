package com.almundo.customerprocessor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public interface CsvReader {
    void forEach(RowProcessor rowProcessor) throws IOException, SQLException;

    void init(String fileName) throws FileNotFoundException;
}
