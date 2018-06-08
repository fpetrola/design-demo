package com.almundo.customerprocessor;

import java.io.File;
import java.io.IOException;
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
    public void persistAddress(Address address) throws SQLException {
        statement.executeUpdate("INSERT INTO address VALUES (" + address.getId() + ",'" + address.getDescription() + "', '" + address.getStreet() + "', '" + address.getNumber() + "', " + address.getCustomerId() + ")");
    }

    @Override
    public void persistCustomer(Customer customer) throws SQLException {
        statement.executeUpdate("INSERT INTO customer VALUES (" + customer.getId() + ",'" + customer.getName() + "', '" + customer.getDni() + "')");
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
        statement.executeUpdate(
                "CREATE TABLE address (id INT NOT NULL, description VARCHAR(50) NOT NULL, street VARCHAR(50) NOT NULL, number VARCHAR(20) NOT NULL, customer INT NOT NULL, PRIMARY KEY (id));");
    }

    @Override
    public Customer findCustomerById(String id) throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT * FROM customer where id='" + id + "'");
        Customer customer = null;
        while (rs.next()) {
            String name = rs.getString("name");
            customer = new Customer("", name, "");
        }
        return customer;
    }

    @Override
    public List<Address> findAddresses() throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT * FROM address");
        List<Address> addresses= new ArrayList<>();
        while (rs.next()) {
            String id = rs.getString("id");
            String description = rs.getString("description");
            String street = rs.getString("street");
            String number = rs.getString("number");
            String customer = rs.getString("customer");
            Address address = new Address(id, description, street, number, customer);

            addresses.add(address);
        }
        return addresses;
    }

}
