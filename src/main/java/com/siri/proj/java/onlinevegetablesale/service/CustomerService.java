package com.siri.proj.java.onlinevegetablesale.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.siri.proj.java.onlinevegetablesale.entity.Customer;
import com.siri.proj.java.onlinevegetablesale.repository.CustomerRepository;


@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository   customerRepository;

	public Customer createCustomer(Customer customer) {
		return  customerRepository.save(customer);
	}

	public List<Customer> getCustomer() {
		return customerRepository.findAll();
	}

	public boolean deleteAllCustomers() {
		try {
			customerRepository.deleteAll();
		}
		catch(Exception e) {
			return false;
		}
		return true;
	}

	public Customer updateCustomer(int customerId, Customer obj) {
		Optional<Customer>  custContainer=customerRepository.findById(customerId);
		if(custContainer.isPresent())
		{
			Customer oldObj=custContainer.get();
			oldObj.setEmailId(obj.getEmailId());
			System.out.println("Succesfully updated!!!!");
			return customerRepository.saveAndFlush(oldObj);
		}
		System.out.println("No object found with these id");
		return obj;
	}

}
