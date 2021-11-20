package com.dhalder.rest.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import com.dhalder.rest.services.UserRepository;

//controller
@RestController
public class UserJPAController {

	@Autowired
	private UserDaoService service;

	@Autowired
	private UserRepository userRepository;
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/jpa/users")
	public List<User> getUsers() {

		return userRepository.findAll();
	}

	@GetMapping(path = "/jpa/users/{userId}")
	public EntityModel<User> getUserById(@PathVariable Integer userId) {

		Optional<User> user =Optional.ofNullable(userRepository.getById(userId));

		if (!user.isPresent()) {
			throw new UserNotFoundException("Id = " + userId);
		}
		WebMvcLinkBuilder links = linkTo(methodOn(this.getClass()).getUsers());
		
		EntityModel<User> model =  EntityModel.of(user.get());
		model.add(links.withRel("users"));
		return model ;
	}

	@DeleteMapping(path = "/jpa/users/{userId}")
	public void deleteUserById(@PathVariable Integer userId) {

		userRepository.deleteById(userId);

		

	}
	
	@GetMapping("/jpa/allusers/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = Optional.ofNullable(userRepository.getById(id));
		
		if(!user.isPresent())
			throw new UserNotFoundException("id-"+ id);
		
		
		//"all-users", SERVER_PATH + "/users"
		//retrieveAllUsers
		EntityModel<User> resource = EntityModel.of(user.get());
		
		WebMvcLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).getUsers());
		
		resource.add(linkTo.withRel("all-users"));
		
		//HATEOAS
		
		return resource;
	}

	@PostMapping(path = "/jpa/users")
	public ResponseEntity<Object> getMessageBeanPath(@Validated @RequestBody User user) {

		User userTemp = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}")
				.buildAndExpand(userTemp.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
}
