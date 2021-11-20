package com.dhalder.rest.beans;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonFilter(value = "SomeFilter")
public class DynaSecurityBean {
	
	private String userId;
	private String role;
	
	private String passcode;
	
	public DynaSecurityBean(String userId, String role, String passcode) {
		super();
		this.userId = userId;
		this.role = role;
		this.passcode = passcode;
	}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPasscode() {
		return passcode;
	}
	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}
	

	
}
