package com.sts.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sts.gateway.repository.UserRepo;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
  
	@Autowired UserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		
		UserDetails user=repo.findByEmail(username);
		
		if(user==null) 
			throw new UsernameNotFoundException("user not exist for given username : "+username);
		
		return user;
	}

	
}
