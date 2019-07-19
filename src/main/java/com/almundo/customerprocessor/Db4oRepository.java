package com.almundo.customerprocessor;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import java.io.File;
import java.util.List;

public class Db4oRepository implements Repository {

    private ObjectContainer objectContainer;

    public void saveCustomer(Customer customer) {
        objectContainer.store(customer);
    }

    public void saveAddress(Address address) {
        objectContainer.store(address);
    }

    public Customer findCustomerById(String id) {
        return objectContainer.query(new Predicate<Customer>() {
            public boolean match(Customer customer) {
                return customer.getId().equals(id);
            }
        }).get(0);
    }

    public List<Address> findAddresses() {
        return objectContainer.query(Address.class);
    }

    public void close() {
        objectContainer.close();
    }

    public void init() throws Exception {
        objectContainer = Db4o.openFile("db4o");
    }
}
