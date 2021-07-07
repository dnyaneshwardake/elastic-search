package com.dnyanesh.elasticsearch.service;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import com.dnyanesh.elasticsearch.model.Customer;

@Service
public class QueryDSLService {

	@Autowired
	private ElasticsearchRestTemplate template;

	public List<Customer> getCustomersByNameAndAge(String firstname, Integer age) {
		QueryBuilder query = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("firstname", firstname))
				.must(QueryBuilders.matchQuery("age", age));
		NativeSearchQuery nativeQuery = new NativeSearchQueryBuilder().withQuery(query).build();
		List<Customer> list = template.queryForList(nativeQuery, Customer.class, IndexCoordinates.of("cust"));

		// SearchHits<Customer> search = template.search(nativeQuery, Customer.class,
		// IndexCoordinates.of("cust"));

		return list;
	}
}
