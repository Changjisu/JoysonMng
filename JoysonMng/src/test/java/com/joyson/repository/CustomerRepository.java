package com.joyson.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.joyson.domain.Customer;

//@Repository
//@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	@Query("SELECT x FROM Customer x ORDER BY x.firstName, x.lastName")
	List<Customer> findAllOrderByName();
	
	@Query(value = "SELECT id, first_name, last_name FROM customers ORDER BY first_name, last_name", nativeQuery = true)
	List<Customer> findAllOrderByName2();

	//@Query(value = "SELECT id, first_name, last_name FROM customers WHERE id = :id ORDER BY first_name, last_name", nativeQuery = true)
	//List<Customer> findOne(Integer id);
	/*
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	SimpleJdbcInsert insert;
	
	@PostConstruct
	public void init() {
		insert = new SimpleJdbcInsert(
				(JdbcTemplate)jdbcTemplate.getJdbcOperations())
				.withTableName("customers")
				.usingGeneratedKeyColumns("id");
	}
	
	private static final RowMapper<Customer> customerRowMapper = (rs, i) -> {
		Integer id = rs.getInt("id");
		String firstName = rs.getString("first_name");
		String lastName = rs.getString("last_name");
		return new Customer(id, firstName, lastName);
	};

	public List<Customer> findAll(){
		List<Customer> customers = jdbcTemplate.query("SELECT id, first_name, last_name FROM customers ORDER BY id", 
				customerRowMapper);
		return customers;
	}
	
	public Customer findOne(Integer id) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		return jdbcTemplate.queryForObject("SELECT id, first_name, last_name FROM customers WHERE id=:id", param, 
				customerRowMapper);
	}
	
	public Customer save(Customer customer) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(customer);
		if(customer.getId() == null) {
			Number key = insert.executeAndReturnKey(param);
			customer.setId(key.intValue());
		} else {
			jdbcTemplate.update("UPDATE customers SET first_name=:firstName, last_name=:lastName WHERE id=:id", param);
		}
		return customer;
	}
	
	public void delete(Integer id) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		jdbcTemplate.update("DELETE FROM customers WHERE id=:id", param);
	}
	*/
}
