package com.sts.api.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sts.api.service.ProductService;
import com.sts.api.entity.ProductDto;
import com.sts.api.entity.ProductResonse;

@RestController
@RequestMapping("/product")
public class ProductController {

	@GetMapping("/test")
	public String hello()
	{
	 return "00hell0o";	
	}
	
	@Autowired
	private ProductService service;
	
	@PostMapping("/add")
	public ProductResonse addProduct(@RequestBody ProductDto dto)
	{
		return service.addProduct(dto);
		
	}	

	@PostMapping("/update")
	public ProductResonse updateProduct(@RequestBody ProductDto dto)
	{
		return service.updateProduct(dto);
		
	}
	
	@DeleteMapping("/remove/{id}")
	public String deletProduct(@PathVariable Integer id)
	{
	 return service.removeProduct(id);
	}
	
	@GetMapping("/{id}")
	public ProductResonse getProductByid(@PathVariable Integer id)
	{
     return service.getProduct(id);	
	}
	
	@GetMapping("/")
	public List<ProductDto> fetchAllProduct()
	{
		return service.getAllProduct();
	}
	
	//@getMapping
	
	
}
