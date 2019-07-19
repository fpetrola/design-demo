package com.almundo.customerprocessor;

import java.util.List;

public class NoDesignCustomerProcessor {

    public void main(Repository repository) throws Exception {

        repository.init();

        CsvReader csvReader = new CsvParserCsvReader();
        csvReader.init("input.txt");

        csvReader.forEach((fields) -> {
            if (fields.get(0).startsWith("C")) {
                repository.saveCustomer(new Customer(fields.get(1), fields.get(2), fields.get(3)));

            } else if (fields.get(0).startsWith("A")) {
                Customer customer = repository.findCustomerById(fields.get(4));
                repository.saveAddress(new Address(fields.get(1), "address of " + customer.getName(), fields.get(2), fields.get(3), customer.getId()));
            }
        });

        List<Address> addresses = repository.findAddresses();
        for (Address address : addresses) {
            System.out.println(address.getId() + " - " + address.getDescription() + " - " + address.getStreet() + " - " + address.getNumber() + " - " + address.getCustomerId());
        }

        repository.close();
    }
}
