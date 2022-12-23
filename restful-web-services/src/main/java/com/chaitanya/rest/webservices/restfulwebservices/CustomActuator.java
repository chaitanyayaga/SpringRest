package com.chaitanya.rest.webservices.restfulwebservices;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id="customActuator")
public class CustomActuator {
    
	@ReadOperation
	public String getCurrentDbDetails() {
		return "Chaitanya DB";
	}
}
