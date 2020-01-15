package com.design.customerprocessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLException;

@Component
public class Jpa2Repository implements Repository {

    private AddressRepository addressRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public Jpa2Repository(AddressRepository addressRepository, CustomerRepository customerRepository) {
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
    }

    public void saveAddress(Address address) {
        addressRepository.save(address);
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer findCustomerById(String id) {
        return customerRepository.findOne(id);
    }

    public void findAddresses(AddressProcessor addressProcessor) {
        addressRepository.findAll().stream().forEach(address -> addressProcessor.processAddress(address));
    }

    public void close() {
    }

    public void init() throws IOException, ClassNotFoundException, SQLException {
    }

    public static interface AddressRepository extends JpaRepository<Address, String> {
    }

    public static interface CustomerRepository extends JpaRepository<Customer, String> {
    }
}
