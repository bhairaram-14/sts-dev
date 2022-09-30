package com.spring.springjwt.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.springjwt.dto.AuthReq;
import com.spring.springjwt.dto.AuthResponse;
import com.spring.springjwt.entity.User;
import com.spring.springjwt.jwt_token.JwtUtility;

@RestController
@RequestMapping("/auth")
public class Authcontroller {

	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtility jwtUtility;
	
	@GetMapping("/")
	public String hello()
	{
		return "hello";
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthReq authReq)
	{
		
		try{ 
			Authentication authentication=authenticationManager.authenticate
					( new UsernamePasswordAuthenticationToken
							   (
									authReq.getUsername(), authReq.getPassword()
							   )
							);
			
			
			
			User user=(User)authentication.getPrincipal();
		    
			//String accessToken="token..............";
			
		    String accessToken=jwtUtility.generateAccessToken(user);
		    AuthResponse authResponse=new AuthResponse(user.getEmail(),accessToken);
		     
		    return ResponseEntity.ok(authResponse);
		}
		catch(BadCredentialsException e ) {
			String error="Unathorized.........  !!\n"
					    +"wrong credential man  !!";
			return ResponseEntity.ok(error);
		}
		

	}

}
