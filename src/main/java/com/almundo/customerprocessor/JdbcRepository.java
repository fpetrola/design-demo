package com.almundo.customerprocessor;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class JdbcRepository implements Repository {

    private Connection conn;
    private Statement statement;

    @Override
    public void saveAddress(Address address) {
        try {
            statement.executeUpdate("INSERT INTO address VALUES (" + address.getId() + "," + address.getDescription() + ", '" + address.getStreet() + "', '" + address.getNumber() + "', " + address.getCustomerId() + ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveCustomer(Customer customer) {
        try {
            statement.executeUpdate("INSERT INTO customer VALUES (" + customer.getId() + ",'" + customer.getName() + "', '" + customer.getDni() + "')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            statement.close();
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init() throws Exception {
        Files.walk(new File("db").toPath()).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);

        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        conn = DriverManager.getConnection("jdbc:hsqldb:file:db/testdb", "SA", "");
        statement = conn.createStatement();

        statement.executeUpdate("CREATE TABLE customer (id INT NOT NULL, name VARCHAR(50) NOT NULL, dni VARCHAR(20) NOT NULL, PRIMARY KEY (id));");
        statement.executeUpdate("CREATE TABLE address (id INT NOT NULL, description VARCHAR(50) NOT NULL, street VARCHAR(50) NOT NULL, number VARCHAR(20) NOT NULL, customer INT NOT NULL, PRIMARY KEY (id));");
    }

    @Override
    public Customer findCustomerById(String id0) {
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM customer where id='" + id0 + "'");
            Customer customer = null;
            if (rs.next()) {

                String id = rs.getString("id");
                String name = rs.getString("name");
                String dni = rs.getString("dni");

                customer = new Customer(id, name, dni);
            }
            return customer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Address> findAddresses() {
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM address");
            List<Address> addresses = new ArrayList<>();

            while (rs.next()) {
                String id = rs.getString("id");
                String description = rs.getString("description");
                String street = rs.getString("street");
                String number = rs.getString("number");
                String customerId = rs.getString("customer");
                Address address = new Address(id, description, street, number, customerId);
                addresses.add(address);
            }
            return addresses;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
