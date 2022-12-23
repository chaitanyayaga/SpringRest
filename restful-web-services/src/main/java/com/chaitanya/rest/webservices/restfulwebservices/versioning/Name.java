package com.chaitanya.rest.webservices.restfulwebservices.versioning;

public class Name {
	
	private String fistname;
	
	private String lastName;

	public Name() {
		super();
	}
	
	public Name(String fistname, String lastName) {
		super();
		this.fistname = fistname;
		this.lastName = lastName;
	}

	public String getFistname() {
		return fistname;
	}

	public void setFistname(String fistname) {
		this.fistname = fistname;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
