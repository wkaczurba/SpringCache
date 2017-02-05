package com.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import com.domain.Customer;

public interface CustomerRepositoryCustom {
	

	@CachePut("customerCache")
	Customer customGet();
	
	// Other stuff: @CachePut (with and without key), @CacheEvict, ... 
}
