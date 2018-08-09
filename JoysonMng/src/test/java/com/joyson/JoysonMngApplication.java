package com.joyson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.joyson.domain.Customer;
import com.joyson.repository.CustomerRepository;

@EnableAutoConfiguration
@ComponentScan /* @Controller나 @Service 애너테이션을 붙인 클래스도 컴포넌트 스캔이 가능합니다. */
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
		
		//Customer created = customerRepository.save(new Customer(null, "Hidetoshi", "Dekisugi"));
		//log.info(created + " is created!");
		
		customerRepository.deleteAll();
		
		customerRepository.save(new Customer(null, "D", "d"));
		customerRepository.save(new Customer(null, "E", "e"));
		customerRepository.save(new Customer(null, "F", "f"));
		customerRepository.save(new Customer(null, "A", "aa"));
		customerRepository.save(new Customer(null, "B", "b"));
		customerRepository.save(new Customer(null, "C", "c"));
		
		Pageable pageable = new PageRequest(0, 3);
		Page<Customer> page = customerRepository.findAll(pageable);
		
		System.out.println("한 페이지당 데이터 수 : " + page.getSize());
		System.out.println("현재 페이지 : " + page.getNumber());
		System.out.println("전체 페이지 수 : " + page.getTotalPages());
		System.out.println("전체 데이터 수 : " + page.getTotalElements());

		page.getContent().forEach(System.out::println);

	}
}
