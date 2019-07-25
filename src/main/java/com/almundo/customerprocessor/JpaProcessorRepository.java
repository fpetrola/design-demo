package com.almundo.customerprocessor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JpaProcessorRepository implements Repository {

	private AddressRepository addressRepository;
	private CustomerRepository customerRepository;

	@Autowired
	public JpaProcessorRepository(AddressRepository addressRepository, CustomerRepository customerRepository) {
		this.addressRepository = addressRepository;
		this.customerRepository = customerRepository;
	}

	public void saveAddress(Address address){
		addressRepository.save(address);
	}

	public void saveCustomer(Customer customer){
		customerRepository.save(customer);
	}

	public void close(){
	}

	public void init() throws IOException, ClassNotFoundException, SQLException {
	}

	public Customer findCustomerById(String id){
		return customerRepository.findOne(id);
	}

	public List<Address> findAddresses(){
		return addressRepository.findAll();
	}

}
