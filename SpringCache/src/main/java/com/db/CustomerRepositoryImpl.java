package com.db;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.domain.Customer;

public class CustomerRepositoryImpl implements CustomerRepositoryCustom {
	
	private static long executions;
	
	@PersistenceContext
	private EntityManager entityManager;	

	@Override
	@Cacheable("customerCache")	
	public Customer customGet() {
		@SuppressWarnings("unchecked")
		TypedQuery<Customer> query = (TypedQuery<Customer>) entityManager.createQuery("select s from Customer s");
		
		Customer result = query.getSingleResult(); 

		System.out.println("customerGet() executing " + (++executions) + " time!; got: " + result);
		
		return result;
	}
	
	public static long getStaticExecutions() {
		return executions;
	}
	
}
