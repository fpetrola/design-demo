package com.almundo.customerprocessor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CustomerProcessor {
    public void main(String[] args) throws Exception {

        Repository repository = new JdbcRepository();
        CsvReader csvReader = new BasicCsvReader();

        process(repository, csvReader);
    }

    public void process(Repository repository, CsvReader csvReader) throws IOException, ClassNotFoundException, SQLException {
        repository.init();
        csvReader.init("input.txt");


        csvReader.forEach(fields -> {
            if (fields.get(0).startsWith("C")) {
                repository.persistCustomer(new Customer(fields.get(1), fields.get(2), fields.get(3)));

            } else if (fields.get(0).startsWith("A")) {
                Customer customer = repository.findCustomerById(fields.get(4));
                repository.persistAddress(new Address(fields.get(1), "address of " + customer.getName(), fields.get(2), fields.get(3), fields.get(4)));
            }
        });

        List<Address> addresses = repository.findAddresses();

        for (Address address : addresses) {
            System.out.println(address.getId() + " - " + address.getDescription() + " - " + address.getStreet() + " - " + address.getNumber() + " - " + address.getCustomerId());
        }

        repository.close();
    }

}
