package com.design.customerprocessor;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Repository {
    void findAddresses(AddressProcessor addressProcessor) throws SQLException;

    Customer findCustomerById(String id);

    void saveAddress(Address address);

    void saveCustomer(Customer customer);

    void close() throws SQLException;

    void init() throws IOException, ClassNotFoundException, SQLException;
}
