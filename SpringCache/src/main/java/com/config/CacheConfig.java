package com.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import net.sf.ehcache.CacheManager;

@Configuration
@EnableCaching
public class CacheConfig {
	
	// EhCacheCacheManager comes from spring-context-support
	@Bean
	public EhCacheCacheManager cacheManager(CacheManager cm) {
		return new EhCacheCacheManager(cm);
	}
	
	// The bean below will end up creating "CacheManager" bean for cacheManager() method.
	@Bean
	public EhCacheManagerFactoryBean ehCache() {
		EhCacheManagerFactoryBean ehCacheFactoryBean = new EhCacheManagerFactoryBean();
		
		ehCacheFactoryBean.setConfigLocation(new ClassPathResource("com/config/ehcache.xml"));
		return ehCacheFactoryBean;
	}
}
