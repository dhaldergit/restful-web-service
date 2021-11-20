package com.dhalder.rest.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dhalder.rest.beans.User;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<User>();
	
	private Integer userCounter = 3;
	static {
		users.add(new User(1, "Dayamay Halder", new Date()));
		users.add(new User(2, "Drishan Halder", new Date()));
		users.add(new User(3, "Srikant Halder", new Date()));
		
		
	}
	
	
	public List<User> getAll(){
		return users;
	}
	
	public User getUserById(Integer id){
		for (User user : users) {
			if(user.getId()==id) {
				return user;
			}
		}
		
		return null;
	}
	
	public User deleteUserById(Integer id){
		
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId()==id) {
				users.remove(user);
				return user;
			}
		}
		
		return null;
	}
	
	
	public User saveUser(User user){
		if(user.getId() ==null) {
			user.setId(++userCounter);
		}
		users.add(user);
		return user;
	}
}
