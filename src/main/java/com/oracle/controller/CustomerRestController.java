package com.oracle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.exceptions.NoSuchCustomerException;
import com.oracle.model.Customer;
import com.oracle.service.CustomerService;

@RestController
@RequestMapping(path = "customer-api")
public class CustomerRestController {
	
	@Autowired
	private CustomerService service;
	
	// http://localhost:9090/customer-api	
	@PostMapping
	public ResponseEntity<String> addCustomer(@RequestBody Customer cust) {
		service.addCustomer(cust);
		ResponseEntity<String> response = new ResponseEntity<String>("Customer added successfully", HttpStatus.CREATED);
		return response;
	}
	
	@GetMapping
	public List<Customer> getAllCustomers(){
		List<Customer> customers = service.findAllCustomers();
		return customers;
	}
	
	@GetMapping(path="/email/{emailId}")
	public Customer getCustomerByEmail(@PathVariable("emailId") String email){
		return service.findCustomerByEmail(email);
	}
	
	@ExceptionHandler(NoSuchCustomerException.class)
	public ResponseEntity<String> handleNoSuchCustomerException(NoSuchCustomerException ex) {
		ResponseEntity<String> response = new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		return response;
	}
	
	@ExceptionHandler(com.oracle.exceptions.DataIntegrityViolationException.class)
	public ResponseEntity<String> DataIntegrityViolationException(com.oracle.exceptions.DataIntegrityViolationException ex) {
		ResponseEntity<String> response = new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		return response;
	}
}
