package com.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.AbstractFileResolvingResource;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;

// TODO: 1. Add JPA with JPA Hibernate adapter (entityManagerFactory, JpaVendorAdapter).
// TODO: 2. Add transactional stuff. 

@Configuration
public class AppConfig {

	// TODO: Embedded and then JDBC/MySQL access.
	
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
			.setType(EmbeddedDatabaseType.H2)
			.addScript("classpath:schema.sql")
			.build();
	}
	
	/*@Bean
	public DataSource dataSource() throws ScriptException, SQLException {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUrl("jdbc:mysql://localhost:3306/hibernate?useSSL=true&requiresSSL=true");
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver"); // com.mysql.jdbc.Driver is deprecated.
		ds.setUsername("root"); // TODO: Get from env variables;
		ds.setPassword("root"); // TODO: Get from env variables;
		//ds.setSchema("classpath:schema.sql");
		
		Resource resource = new ClassPathResource("schema.sql");
		ScriptUtils.executeSqlScript(ds.getConnection(), resource);
		
		return ds;
	}*/
	
	// TODO: add DBCP 
	
	/*public LocalContainerManagerFactoryBean entityManagerFactory() {
		
	}*/
}
