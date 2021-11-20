package com.dhalder.rest.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

	//1ST   Approach
	@GetMapping("/person/v1")
	public PersonV1 getPersonV1() {
		return new PersonV1("Dayamay Halder");
	}
	
	@GetMapping("/person/v2")
	public PersonV2 getPersonV2() {
		return new PersonV2( new Name( "Dayamay",  "Halder"));
	}
	
	
	
	//@2nd Approach
	
		@GetMapping(value = "/person/param" , params = "version=1")
		public PersonV1 getPersonParam1() {
			return new PersonV1("Dayamay Halder");
		}
		
		@GetMapping(value = "/person/param" , params = "version=2")
		public PersonV2 getPersonParam2() {
			return new PersonV2( new Name( "Dayamay",  "Halder"));
		}
		
		//3RD Approach
		
				@GetMapping(value = "/person/header" , headers =  "X-API-VERSION=1")
				public PersonV1 getPersonHeader1() {
					return new PersonV1("Dayamay Halder");
				}
				
				@GetMapping(value = "/person/header" , headers =  "X-API-VERSION=2")
				public PersonV2 getPersonHeader2() {
					return new PersonV2( new Name( "Dayamay",  "Halder"));
				}
				
				//4TH Approach
				
				@GetMapping(value = "/person/produces" , produces  =  "application/vnd.company.app-v1+json")
				public PersonV1 produces1() {
					return new PersonV1("Dayamay Halder");
				}
				
				@GetMapping(value = "/person/produces" ,  produces  =  "application/vnd.company.app-v2+json")
				public PersonV2 produces2() {
					return new PersonV2( new Name( "Dayamay",  "Halder"));
				}
}
