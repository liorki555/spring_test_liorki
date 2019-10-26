package com.lior.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lior.spring.db.exceptions.DBResourceNotFoundException;
import com.lior.spring.db.models.Customer;
import com.lior.spring.db.services.CustomerService;

@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {
	protected static final String BASE_URL = "/customer";
	@Autowired
	private CustomerService customerService;
	
	public CustomerController() {
	}
	
	@RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
	public Customer getCustomer(@PathVariable long customerId) throws DBResourceNotFoundException {
		System.out.println("CustomerController - getCustomer(" + customerId + "), has been requested!");
		return customerService.findById(customerId);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Customer> getAllCustomers() {
		System.out.println("CustomerController getAllCustomers(), has been requested!");
		return customerService.findAll();
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public String addCustomer(@RequestBody Customer customer) throws DBResourceNotFoundException {
		System.out.println("CustomerController addCustomer(" + customer + "), has been requested!");
		customerService.createCustomer(customer);
		return "putCustomer_method_PUT";
	}
	
	@RequestMapping(value = "/2", method = RequestMethod.POST)
	public String updateCustomer(@RequestBody Customer customer) throws DBResourceNotFoundException {
		System.out.println("CustomerController updateCustomer(" + customer + "), has been requested!");
		customerService.updateCustomer(customer);
		return "updateCustomer_method_POST";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteCustomer(@PathVariable long id) throws DBResourceNotFoundException {
		System.out.println("CustomerController deleteCustomer(" + id + "), has been requested!");
		customerService.deleteCustomer(id);
		return "deleteCustomer_method_DELETE";
	}
}