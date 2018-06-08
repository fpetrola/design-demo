package com.almundo.customerprocessor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class BasicCsvReader implements CsvReader {

    private FileReader fileReader;
    private LineNumberReader lineNumberReader;

    @Override
    public void forEach(RowProcessor rowProcessor) throws IOException, SQLException {
        String line = lineNumberReader.readLine();

        while (line != null) {
            List<String> fields = Arrays.asList(line.split(","));

            rowProcessor.processRow(fields);
            line = lineNumberReader.readLine();
        }

        lineNumberReader.close();
    }

    @Override
    public void init(String fileName) throws FileNotFoundException {
        fileReader = new FileReader(fileName);

        lineNumberReader = new LineNumberReader(fileReader);
    }
}
