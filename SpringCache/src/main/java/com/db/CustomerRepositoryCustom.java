package com.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.Customer;

public interface CustomerRepositoryCustom {
	Customer customGet();
}
