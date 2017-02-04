package com.db;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.domain.Customer;

public class CustomerRepositoryImpl implements CustomerRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;	

	@Override
	public Customer customGet() {
		@SuppressWarnings("unchecked")
		TypedQuery<Customer> query = (TypedQuery<Customer>) entityManager.createQuery("select s from Customer s");
		
		return query.getSingleResult();
		
		// TODO Auto-generated method stub
		//System.out.println("custom get called...");
		//throw new IllegalStateException("The function requires implementing...");
	}
	
}
