package com.design.customerprocessor;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;

public class Db4ORepository implements Repository {

    private ObjectContainer objectContainer;

    public void saveCustomer(Customer customer) {
        objectContainer.store(customer);
    }

    public void saveAddress(Address address) {
        objectContainer.store(address);
    }

    public Customer findCustomerById(String id) {
        return objectContainer.query(Customer.class).stream().filter(customer -> customer.getId().equals(id)).findFirst().get();
    }

    public void findAddresses(AddressProcessor addressProcessor) {
        objectContainer.query(Address.class).forEach(address -> addressProcessor.processAddress(address));
    }

    public void close() {
        objectContainer.close();
    }

    public void init() {
        objectContainer = Db4o.openFile("db4o");
    }
}
