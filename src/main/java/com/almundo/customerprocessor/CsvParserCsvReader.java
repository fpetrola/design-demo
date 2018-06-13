package com.almundo.customerprocessor;

import org.apache.commons.csv.CSVParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.csv.CSVFormat.DEFAULT;

public class CsvParserCsvReader implements CsvReader {

	private CSVParser csvParser;

	public void forEach(RowProcessor rowProcessor) throws IOException, SQLException {
		csvParser.forEach(fields -> {
			List<String> list = new ArrayList<>();
			fields.forEach(list::add);
			try {
				rowProcessor.processRow(list);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
	}

	public void init(String fileName) throws FileNotFoundException {
		try {
			csvParser = new CSVParser(new FileReader(fileName), DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}