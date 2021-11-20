package com.dhalder.rest.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhalder.rest.beans.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
