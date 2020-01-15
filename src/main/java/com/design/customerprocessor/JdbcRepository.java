package com.design.customerprocessor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.Comparator;

public class JdbcRepository implements Repository {

    private Connection conn;
    private Statement statement;

    @Override
    public void findAddresses(AddressProcessor addressProcessor) throws SQLException {
        ResultSet rs = selectAddresses();
        while (rs.next()) {
            addressProcessor.processAddress(new Address(rs.getString("id"), rs.getString("description"), rs.getString("street"), rs.getString("number"), rs.getString("customer")));
        }
    }

    @Override
    public Customer findCustomerById(String id) {
        Customer customer = null;
        ResultSet rs = null;
        try {
            rs = statement.executeQuery("SELECT * FROM customer where id='" + id + "'");
            if (rs.next()) {
                customer = new Customer(rs.getString("id"), rs.getString("name"), rs.getString("dni"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public ResultSet selectAddresses() throws SQLException {
        return statement.executeQuery("SELECT * FROM address");
    }

    @Override
    public void saveAddress(Address address){
        try {
            statement.executeUpdate("INSERT INTO address VALUES (" + address.getId() + ",'" + address.getDescription() + "', '" + address.getStreet() + "', '" + address.getNumber() + "', " + address.getCustomer() + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveCustomer(Customer customer) {
        try {
            statement.executeUpdate("INSERT INTO customer VALUES (" + customer.getId() + ",'" + customer.getName() + "', '" + customer.getDni() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws SQLException {
        statement.close();
        conn.commit();
        conn.close();
    }

    @Override
    public void init() throws IOException, ClassNotFoundException, SQLException {
        Files.walk(new File("db").toPath()).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);

        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        conn = DriverManager.getConnection("jdbc:hsqldb:file:db/testdb", "SA", "");
        statement = conn.createStatement();

        statement.executeUpdate("CREATE TABLE customer (id INT NOT NULL, name VARCHAR(50) NOT NULL, dni VARCHAR(20) NOT NULL, PRIMARY KEY (id));");
        statement.executeUpdate("CREATE TABLE address (id INT NOT NULL, description VARCHAR(50) NOT NULL, street VARCHAR(50) NOT NULL, number VARCHAR(20) NOT NULL, customer INT NOT NULL, PRIMARY KEY (id));");
    }
}
