package com.design.customerprocessor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Address {
    @Id
    private  String id;
    private  String description;
    private  String street;
    private  String number;
    private  String customer;

    public Address() {
    }

    public Address(String id, String description, String street, String number, String customer) {
        this.id = id;
        this.description = description;
        this.street = street;
        this.number = number;
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getCustomer() {
        return customer;
    }
}