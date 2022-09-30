package com.spring.springjwt.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.springjwt.entity.Product;
import com.spring.springjwt.repository.ProductRepo;

@RestController
@RequestMapping("/product")
public class productController {

	@Autowired
	ProductRepo productRepo;
	
	@GetMapping("/")
	public String hello()
	{
	 return "heloloo";	
	}
	
	@GetMapping
    @RolesAllowed({"ROLE_EDITOR" , "ROLE_CUSTOMER"})
 	public List<Product> fetch()
	{

		System.out.print("\n\nhello-3-get\n\n\n");
		return productRepo.findAll();
	}
	
	@PostMapping
	@RolesAllowed("ROLE_ADMIN")
	public ResponseEntity<Product> create(@RequestBody @Valid Product product)
	{
		System.out.print("\n\nhello-3-post");
		product=productRepo.save(product);
		URI productURI= URI.create("/product/"+product.getId());
		return ResponseEntity.created(productURI).body(product); 
	}
	
}
