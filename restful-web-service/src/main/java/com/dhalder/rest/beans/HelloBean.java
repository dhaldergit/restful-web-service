package com.dhalder.rest.beans;

public class HelloBean {
	
	
	private String message;

	public HelloBean() {
		// TODO Auto-generated constructor stub
	}
	public HelloBean(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		 
		return super.toString();
	}
	public String getMessage() {
		return message; 
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

}
