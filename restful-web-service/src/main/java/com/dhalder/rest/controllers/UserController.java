package com.dhalder.rest.controllers;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dhalder.rest.beans.User;
import com.dhalder.rest.exception.UserNotFoundException;
import com.dhalder.rest.services.UserDaoService;

//controller
@RestController
public class UserController {

	@Autowired
	private UserDaoService service;

	@RequestMapping(method = RequestMethod.GET, path = "/users")
	public List<User> getUsers() {

		return service.getAll();
	}

	@GetMapping(path = "/users/{userId}")
	public EntityModel<User> getUserById(@PathVariable Integer userId) {

		User user = service.getUserById(userId);

		if (user == null) {
			throw new UserNotFoundException("Id = " + userId);
		}
		WebMvcLinkBuilder links = linkTo(methodOn(this.getClass()).getUsers());
		
		EntityModel<User> model =  EntityModel.of(user);
		model.add(links.withRel("users"));
		return model ;
	}

	@DeleteMapping(path = "/users/{userId}")
	public void deleteUserById(@PathVariable Integer userId) {

		User user = service.deleteUserById(userId);

		if (user == null) {
			throw new UserNotFoundException("Id = " + userId);
		}

	}
	
	@GetMapping("/allusers/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user = service.getUserById(id);
		
		if(user==null)
			throw new UserNotFoundException("id-"+ id);
		
		
		//"all-users", SERVER_PATH + "/users"
		//retrieveAllUsers
		EntityModel<User> resource = EntityModel.of(user);
		
		WebMvcLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).getUsers());
		
		resource.add(linkTo.withRel("all-users"));
		
		//HATEOAS
		
		return resource;
	}

	@PostMapping(path = "/users")
	public ResponseEntity<Object> getMessageBeanPath(@Validated @RequestBody User user) {

		User userTemp = service.saveUser(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}")
				.buildAndExpand(userTemp.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
}
