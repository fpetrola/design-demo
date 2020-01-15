package com.design.customerprocessor;

import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.csv.CSVParser;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import static org.apache.commons.csv.CSVFormat.DEFAULT;

public class CsvParserCsvReader implements CsvReader {
    private CSVParser csvParser;

    public void forEachRow(RowProcessor rowProcessor) throws IOException, SQLException {
        csvParser.forEach(fields -> rowProcessor.processRow(IteratorUtils.toList(fields.iterator())));
    }
    public void load(String fileName) throws Exception {
        csvParser = new CSVParser(new FileReader(fileName), DEFAULT);
    }
}