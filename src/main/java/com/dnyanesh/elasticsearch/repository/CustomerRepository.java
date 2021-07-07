package com.dnyanesh.elasticsearch.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.dnyanesh.elasticsearch.model.Customer;

public interface CustomerRepository extends ElasticsearchRepository<Customer, String> {

	List<Customer> findByFirstname(String firstName);

}
