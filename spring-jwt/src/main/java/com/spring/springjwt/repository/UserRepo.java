package com.spring.springjwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.springjwt.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	User findByEmail(String email);
	
	
}
