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

import com.sts.api.common.OrderDto;
import com.sts.api.entity.Order;
import com.sts.api.service.OrderService;


@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired	
	private OrderService service;
	@GetMapping("/test")
	public String test()
	{
		return "hello";	 
	}

	@PostMapping("/createOrder")
	public OrderDto createOrder(@RequestBody OrderDto  dto)
	{

		return service.createOrder(dto);
	}

	@PostMapping("/placeOrder")
	public OrderDto placeOrder(@RequestBody OrderDto  dto)
	{

		return service.placeOrder(dto);
	}

	@PostMapping("/delever")
	public OrderDto deleverOrder(@RequestBody OrderDto  dto)
	{

		return service.deleverOrder(dto);
	}
	
	
	
	@GetMapping("/")
	public List<Order> fetchOrder()
	{ 
		return service.getAllOrder();
	}

	
	 
	  @GetMapping("/{id}") 
	  public Order getOrder(@PathVariable int id) 
	  { 
		  return service.getById(id); 
	  }
	  
	  @DeleteMapping("/cancelOrder")
	  public String cancelOrder(@PathVariable int id)
	  { 
		  return service.cancelOrder(id); 
	  }
	 
}
