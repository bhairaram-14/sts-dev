package com.sts.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sts.app.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	User findByUsername(String name);

}
