package com.app.book.cache.config;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {
	
	@Bean
	public CacheManagerCustomizer<ConcurrentMapCacheManager> customizer() {
		return new ConcurrentCustomizer();
	}
}
