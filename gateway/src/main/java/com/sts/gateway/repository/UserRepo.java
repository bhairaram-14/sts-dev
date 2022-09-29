package com.sts.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sts.gateway.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> 
{
   User findByEmail(String userName);
}

