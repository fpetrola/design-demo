package com.design.customerprocessor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public interface CsvReader {
    void forEachRow(RowProcessor rowProcessor) throws IOException, SQLException;

    void load(String filename) throws FileNotFoundException, Exception;
}
