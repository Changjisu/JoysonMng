package com.joyson.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyson.domain.Customer;
import com.joyson.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	public Customer save(Customer customer){
		return customerRepository.save(customer);
	}
	
	public List<Customer> findAll(){
		return customerRepository.findAllOrderByName();
	}
	
	public Page<Customer> findAll(Pageable pageable){
		return customerRepository.findAll(pageable);
	}
	
	//Optional
	public Optional<Customer> findById(Integer id) {
		return customerRepository.findById(id);
	}
	
	public Customer create(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public Customer update(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public void delete(Integer id) {
		customerRepository.deleteById(id);
	}
}
