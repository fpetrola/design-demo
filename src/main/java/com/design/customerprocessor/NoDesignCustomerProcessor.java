package com.design.customerprocessor;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class NoDesignCustomerProcessor {
    public static void main(String[] args) throws Exception {

        Files.walk(new File("db").toPath()).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);

        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        Connection conn = DriverManager.getConnection("jdbc:hsqldb:file:db/testdb", "SA", "");
        Statement statement = conn.createStatement();

        statement.executeUpdate("CREATE TABLE customer (id INT NOT NULL, name VARCHAR(50) NOT NULL, dni VARCHAR(20) NOT NULL, PRIMARY KEY (id));");
        statement.executeUpdate("CREATE TABLE address (id INT NOT NULL, description VARCHAR(50) NOT NULL, street VARCHAR(50) NOT NULL, number VARCHAR(20) NOT NULL, customer INT NOT NULL, PRIMARY KEY (id));");

        FileReader fileReader = new FileReader("input.txt");

        LineNumberReader lineNumberReader = new LineNumberReader(fileReader);

        String line = lineNumberReader.readLine();

        while (line != null) {
            List<String> fields = Arrays.asList(line.split(","));

            if (fields.get(0).startsWith("C")) {
                statement.executeUpdate("INSERT INTO customer VALUES (" + fields.get(1) + ",'" + fields.get(2) + "', '" + fields.get(3) + "')");

            } else if (fields.get(0).startsWith("A")) {
                ResultSet rs = statement.executeQuery("SELECT * FROM customer where id='" + fields.get(4) + "'");
                if (rs.next()) {
                    statement.executeUpdate("INSERT INTO address VALUES (" + fields.get(1) + ",'" + "address of " + rs.getString("name") + " - " + rs.getString("dni") + "', '" + fields.get(2) + "', '" + fields.get(3) + "', " + rs.getString("id") + ")");
                }
            }
            line = lineNumberReader.readLine();
        }

        lineNumberReader.close();

        ResultSet rs = statement.executeQuery("SELECT * FROM address");
        while (rs.next()) {
            System.out.println(rs.getString("id") + " - " + rs.getString("description") + " - " + rs.getString("street") + " - " + rs.getString("number") + " - " + rs.getString("customer"));
        }

        statement.close();
        conn.commit();
        conn.close();
    }
}
