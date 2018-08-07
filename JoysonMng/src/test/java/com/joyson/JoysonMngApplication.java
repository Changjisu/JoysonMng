package com.joyson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.joyson.domain.Customer;
import com.joyson.repository.CustomerRepository;

@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class JoysonMngApplication implements CommandLineRunner{

	@Autowired
	CustomerRepository customerRepository;
	
	/*
	@Autowired
	CustomerService customerService;
	*/
	
	static final Logger log = LoggerFactory.getLogger(JoysonMngApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(JoysonMngApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Customer created = customerRepository.save(new Customer(null, "Hidetoshi", "Dekisugi"));
		log.info(created + " is created!");
		
		customerRepository.findAll().forEach(System.out::println);

	}
}
