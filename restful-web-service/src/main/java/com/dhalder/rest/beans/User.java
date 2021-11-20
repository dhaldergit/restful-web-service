package com.dhalder.rest.beans;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;


@Entity
@Table(name = "T_USER")
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class User {
	
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min = 2, message = "The name must be atleast 2 characters long")
	private String  name;
	
	
	@Past
	private Date birthDate ;
	public Integer getId() {
		return id;
	}
	
	
	
	
	public User() {
		super();
	}




	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	
	
	

}
