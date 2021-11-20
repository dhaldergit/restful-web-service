package com.dhalder.rest.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dhalder.rest.beans.HelloBean;

//controller
@RestController("/service")
public class HelloWorld {

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET, path = "/hello")
	public String getMessage() {

		return "Hello World";
	}

	@GetMapping(path = "/hello-bean")
	public HelloBean getMessageBean() {

		return new HelloBean("Hello World from Bean");
	}

	@GetMapping(path = "/hello-bean/path/{name}")
	public HelloBean getMessageBeanPath(@PathVariable String name) {

		return new HelloBean(String.format("Hello World from Bean with path %s", name));
	}

	@RequestMapping(method = RequestMethod.GET, path = "/hello-internacionalized")
	public String getMessageInternacionalized(
	// @RequestHeader(name = "Accept-Language", required = false) Locale locale //
	// alternate way below
	) {

//		return messageSource.getMessage("app.hello.message", null, "Default Message", locale);
		return messageSource.getMessage("app.hello.message", null, "Default Message", LocaleContextHolder.getLocale());

	}
}
