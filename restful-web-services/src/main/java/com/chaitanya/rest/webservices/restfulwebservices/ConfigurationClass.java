package com.chaitanya.rest.webservices.restfulwebservices;

import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationClass {
	
	@Bean
	public HttpTraceRepository httpTracerepository() {
		return new InMemoryHttpTraceRepository();
	}

}
