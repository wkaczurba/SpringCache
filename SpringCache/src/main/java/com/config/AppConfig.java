package com.config;

import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
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
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// TODO: 1. Add JPA with JPA Hibernate adapter (entityManagerFactory, JpaVendorAdapter).
// TODO: 2. Add transactional stuff. 

@Configuration
@ComponentScan(basePackages="com") // TO LOAD SpringDataJpaConfig which enables @EnableJpaRepository
@EnableTransactionManagement
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
	
	// Requires: org.springframework.data:spring-data-jpa
	// To set: dataSource, persistenceUnitName, jpaVendorAdapter, packagesToScan
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource);
		emf.setPersistenceUnitName("CustomerPU");
		emf.setJpaVendorAdapter(jpaVendorAdapter());
		emf.setPackagesToScan("com.domain");
		return emf;
	}
	
	// Requires: org.springframework.data:spring-data-jpa
	// To set: database, databasePlatform (dialect), setShowSql(true), setGenerateDdl(false)
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.H2); // TODO: MySQL DB support: Database.MySQL
		adapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect"); // TODO: MySQL DB support: org.hibernate.dialect.MySQL57InnoDBDialect
		
		adapter.setShowSql(true);
		adapter.setGenerateDdl(false);
		return adapter;
	}
	
	// NOT SURE IF CORRECT:
	@Bean
	//public PlatformTransactionManager platformTransactionManager(EntityManagerFactory emf) {
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}
}
