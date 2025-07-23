package com.oracle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.dao.CustomerDao;
import com.oracle.exceptions.NoSuchCustomerException;
import com.oracle.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerDao dao;
	
	@Override
	public void addCustomer(Customer cust) {
		dao.createCustomer(cust);
	}

	@Override
	public Customer findCustomerByEmail(String email) {
		Customer customer = dao.readCustomerByEmail(email);
		if(customer == null) {
			throw new NoSuchCustomerException("Customer with email " + email + " not found");
		}
		return customer;
	}

	@Override
	public List<Customer> findAllCustomers() {
		return dao.readAllCustomers();
	}

}
