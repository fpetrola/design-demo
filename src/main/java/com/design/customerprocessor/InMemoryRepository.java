package com.design.customerprocessor;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository implements Repository {
    private List<Object> entities;

    public void saveCustomer(Customer customer) {
        entities.add(customer);
    }

    public void saveAddress(Address address) {
        entities.add(address);
    }

    public Customer findCustomerById(String id) {
        return (Customer) entities.stream().filter(v -> v instanceof Customer && ((Customer) v).getId().equals(id)).findFirst().get();
    }

    public void findAddresses(AddressProcessor addressProcessor) {
        entities.stream().filter(v -> v instanceof Address).forEach(a -> addressProcessor.processAddress((Address) a));
    }

    public void close() {
    }

    public void init() {
        entities = new ArrayList<>();
    }
}
