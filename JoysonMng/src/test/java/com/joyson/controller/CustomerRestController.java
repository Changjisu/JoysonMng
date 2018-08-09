package com.joyson.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.joyson.domain.Customer;
import com.joyson.service.CustomerService;

@RestController
@RequestMapping("api/customers")
public class CustomerRestController {

	@Autowired
	CustomerService customerService;
	
	@RequestMapping(method = RequestMethod.GET)
	List<Customer> getCustomers(){
		List<Customer> customers = customerService.findAll();
		return customers;
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	Optional<Customer>  getCustomer(@PathVariable Integer id) {
		Optional<Customer>  customer = customerService.findById(id);
		return customer;
	}
}
