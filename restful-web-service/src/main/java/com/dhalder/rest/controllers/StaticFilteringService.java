package com.dhalder.rest.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhalder.rest.beans.SecurityBean;

@RestController
public class StaticFilteringService {

	@GetMapping(path = "/user-role" )
	public SecurityBean getSecurityInfo() {
		return new SecurityBean("dhalder", "Architect", "PasswordTest");
	}
	
	@GetMapping(path = "/user-roles" )
	public List<SecurityBean> getSecurityRoles() {
		return Arrays.asList( new SecurityBean("dhalder", "Architect", "PasswordTest") 
							, new SecurityBean("ccheck", "Solution Architect", "TestWordTest") 
							);
	}
}
