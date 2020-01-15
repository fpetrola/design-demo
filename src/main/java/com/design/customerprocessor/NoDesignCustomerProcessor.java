package com.design.customerprocessor;

import java.io.IOException;
import java.sql.SQLException;

public class NoDesignCustomerProcessor {

    public void process(Repository repository, CsvReader csvReader) throws IOException, SQLException {
        csvReader.forEachRow(fields -> {
            if (fields.get(0).startsWith("C")) {
                repository.saveCustomer(new Customer(fields.get(1), fields.get(2), fields.get(3)));

            } else if (fields.get(0).startsWith("A")) {
                Customer customer = repository.findCustomerById(fields.get(4));
                repository.saveAddress(new Address(fields.get(1), "address of " + customer.getName() + " - " + customer.getDni(), fields.get(2), fields.get(3), customer.getId()));
            }
        });

        repository.findAddresses(address -> System.out.println(address.getId() + " - " + address.getDescription() + " - " + address.getStreet() + " - " + address.getNumber() + " - " + address.getCustomer()));
    }

}
