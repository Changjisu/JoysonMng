package com.joyson;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.joyson.domain.Customer;

@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class JoysonMngApplication implements CommandLineRunner{

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	/*
	@Autowired
	CustomerService customerService;
	*/
	
	public static void main(String[] args) {
		SpringApplication.run(JoysonMngApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		/*
		customerService.save(new Customer(1, "Nobita", "Nobi"));
		customerService.save(new Customer(2, "Takeshi", "Goda"));
		customerService.save(new Customer(3, "Suneo", "Honekawa"));
		
		customerService.findAll().forEach(System.out::println);
		*/
		
		
		String sql = "SELECT id, first_name, last_name FROM customers WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("id", 1);
		
		
		
		Customer result = jdbcTemplate.queryForObject(sql, param, (rs, rowNum) -> new Customer(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name")));
		
		
		System.out.println("result=" + result);
		
	}
}
