package com.almundo.customerprocessor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface Repository {
    void persistAddress(Address address) throws SQLException;

    void persistCustomer(Customer customer) throws SQLException;

    void close() throws SQLException;

    void init() throws IOException, ClassNotFoundException, SQLException;

    Customer findCustomerById(String id) throws SQLException;

    List<Address> findAddresses() throws SQLException;
}
