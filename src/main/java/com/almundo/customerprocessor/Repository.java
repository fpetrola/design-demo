package com.almundo.customerprocessor;

import java.sql.SQLException;
import java.util.List;

public interface Repository {
    void saveAddress(Address address) ;

    void saveCustomer(Customer customer) ;

    void close() ;

    void init() throws Exception;

    Customer findCustomerById(String id0) ;

    List<Address> findAddresses() ;
}
