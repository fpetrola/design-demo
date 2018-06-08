package com.almundo.customerprocessor;

public class Address {
    private final String id;
    private final String description;
    private final String street;
    private final String number;
    private final String customerId;

    public Address(String id, String description, String street, String number, String customerId) {
        this.id = id;
        this.description = description;
        this.street = street;
        this.number = number;
        this.customerId = customerId;
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

    public String getCustomerId() {
        return customerId;
    }
}
