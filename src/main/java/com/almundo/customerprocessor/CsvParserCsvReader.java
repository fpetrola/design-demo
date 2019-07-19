package com.almundo.customerprocessor;

import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.csv.CSVParser;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import static org.apache.commons.csv.CSVFormat.DEFAULT;

public class CsvParserCsvReader implements CsvReader {
    private CSVParser csvParser;

    public void forEach(RowProcessor rowProcessor) throws IOException, SQLException {
        csvParser.forEach(fields -> rowProcessor.processLine(IteratorUtils.toList(fields.iterator())));
    }
    public void init(String fileName) throws Exception {
        csvParser = new CSVParser(new FileReader(fileName), DEFAULT);
    }
}