package com.almundo.customerprocessor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {
	@Id
	private String id;
	private String name;
	private String dni;

	public Customer() {
	}

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
