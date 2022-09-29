package com.sts.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class controller {

	@GetMapping("/test")
	public String test()
	{
		
	 System.out.print("hello");	
	 return "testing......";	
	}
	

	@GetMapping("/login")
	public String welcome()
	{
	 return "welcome......";	
	}
	
}
