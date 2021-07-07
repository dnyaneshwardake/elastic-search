package com.dnyanesh.elasticsearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dnyanesh.elasticsearch.model.Customer;
import com.dnyanesh.elasticsearch.repository.CustomerRepository;
import com.dnyanesh.elasticsearch.service.QueryDSLService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerRepository repository;
	@Autowired
	private QueryDSLService dslService;

	@PostMapping("/save")
	public int saveCustomer(@RequestBody List<Customer> customers) {
		repository.saveAll(customers);
		return customers.size();
	}

	@GetMapping("/findall")
	public Iterable<Customer> findAllCustomers() {
		return repository.findAll();
	}

	@GetMapping("/findbyname/{firstname}")
	public List<Customer> findByFirstName(@PathVariable String firstname) {
		return repository.findByFirstname(firstname);
	}

	@GetMapping("/findby/{firstname}/{age}")
	public List<Customer> findByFirstNameAndAge(@PathVariable String firstname, @PathVariable Integer age) {
		return dslService.getCustomersByNameAndAge(firstname, age);
	}

}
