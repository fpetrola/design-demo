package com.almundo.customerprocessor;

public class Customer {
    private final String id;
    private final String name;
    private final String dni;

    public Customer(String id, String name, String dni) {
        this.id = id;
        this.name = name;
        this.dni = dni;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDni() {
        return dni;
    }
}
