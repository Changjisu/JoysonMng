package com.joyson.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.joyson.domain.Customer;
import com.joyson.service.CustomerService;

@RestController
@RequestMapping("api/customers")
public class CustomerRestController {

	@Autowired
	CustomerService customerService;
	
	@RequestMapping(method = RequestMethod.GET)
	Page<Customer> getCustomers(@PageableDefault Pageable pageable){
		Page<Customer> customers = customerService.findAll(pageable);
		return customers;
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	Optional<Customer>  getCustomer(@PathVariable Integer id) {
		Optional<Customer>  customer = customerService.findById(id);
		return customer;
	}
	
	//insert
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<Customer> postCustomers(@RequestBody Customer customer, UriComponentsBuilder uriBuilder) {
		
		Customer created = customerService.create(customer);
		URI location = uriBuilder.path("api/customers/{id}").buildAndExpand(created.getId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		return new ResponseEntity<>(created, headers, HttpStatus.CREATED);
	}
	
	//update
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	Customer putCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
		customer.setId(id);
		return customerService.update(customer);
	}
	
	//delete
	@RequestMapping(value = "{id}",method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteCustomer(@PathVariable Integer id) {
		customerService.delete(id);
	}
}
