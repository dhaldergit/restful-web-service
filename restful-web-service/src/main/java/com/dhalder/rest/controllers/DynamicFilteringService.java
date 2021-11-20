package com.dhalder.rest.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhalder.rest.beans.DynaSecurityBean;
import com.dhalder.rest.beans.SecurityBean;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class DynamicFilteringService {

	@GetMapping(path = "/userrole")
	public MappingJacksonValue getSecurityInfo() {
		DynaSecurityBean securityInfoBean = new DynaSecurityBean("dhalder", "Architect", "PasswordTest");

		MappingJacksonValue jacksonValue = new MappingJacksonValue(securityInfoBean);

		SimpleBeanPropertyFilter simpleFilter = SimpleBeanPropertyFilter.filterOutAllExcept("role", "passcode");

		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeFilter", simpleFilter);

		jacksonValue.setFilters(filters);

		return jacksonValue;
	}

	@GetMapping(path = "/userrolelist")
	public MappingJacksonValue getSecurityRoles() {
		List beanList= Arrays.asList(new DynaSecurityBean("dhalder", "Architect", "PasswordTest"),
				new DynaSecurityBean("ccheck", "Solution Architect", "TestWordTest"));
		

		MappingJacksonValue jacksonValue = new MappingJacksonValue(beanList);

		SimpleBeanPropertyFilter simpleFilter = SimpleBeanPropertyFilter.filterOutAllExcept("role", "userId");

		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeFilter", simpleFilter);

		jacksonValue.setFilters(filters);

		return jacksonValue;
	}
}
