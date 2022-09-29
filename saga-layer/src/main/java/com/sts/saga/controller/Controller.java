package com.sts.saga.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sts.saga.dtos.Order;
import com.sts.saga.dtos.OrderResponse;
import com.sts.saga.dtos.Payment;
import com.sts.saga.dtos.PaymentResponse;
import com.sts.saga.dtos.Product;
import com.sts.saga.dtos.ProductResponse;
import com.sts.saga.service.OrderService;
import com.sts.saga.service.PaymentService;
//import com.sts.saga.service.Service;
import com.sts.saga.service.ProductService;

@RestController
@RequestMapping("/saga")
public class Controller {


	@Autowired 
	private ProductService productService;

	@Autowired 
	private PaymentService paymentservice;

	@Autowired 
	private OrderService orderService;

	
//===========================test====================	
	
	@GetMapping("/") 
	public String test() 
	{
		return "WELCOME --------MAN.....!!";
	}
	
//=====================================payment===========================	
	

	

	@PostMapping("/paykro")
	public PaymentResponse dopayment(@RequestBody Payment payment)
	{
		
	  return	paymentservice.pay(payment);
		
	}

	@GetMapping("/fecthPayment/{id}")
	public PaymentResponse getPaymentById(@PathVariable Integer id) 
	{
		return paymentservice.fetchByPaymentId(id);
		
		
	}
	
	@GetMapping("/payment")
	public List<Payment> fetchAllPayments()
	{
		return paymentservice.fetchAll();
	}


	@GetMapping("/fecthPaymentbyOrderId/{id}")
	public List<Payment> getPaymentByOrderId(@PathVariable Integer id) 
	{
		return paymentservice.fetchPaymentByOrderId(id);
		
		
	}


	
	
//=========================products========================================================

	
	
	
	
	
	@PostMapping("/addproduct")
	public ProductResponse addproduct(@RequestBody Product product)
	{
		return productService.addProduct(product);		
	}

	@DeleteMapping("/deleteproduct/{pid}")
	public String deleteProduct(@PathVariable Integer pid)
	{
		ProductResponse dto=productService.fetchProductById(pid);

		if(dto.getDto()==null)
			return dto.getMessage();  
		productService.delProduct(pid);
		if(productService.fetchProductById(pid).getDto()!=null)
			return "something went wronng with your code";

		return "product successfully deleted having id : "+pid; 
	}

	@GetMapping("/fetchproducts")
	public List<Product> fetchAllProducts()
	{

		return productService.fetchAllProduct();
	}

	@GetMapping("/fetchproduct/{id}")
	public ProductResponse fetchProductById(@PathVariable Integer id)
	{
		
		return productService.fetchProductById(id);
	}

//======================================== order ===================================

	
	@PostMapping("/reqorder")
	public Order addOrder(@RequestBody Order order)
	{
	  return orderService.createOrder(order);	
	 	
	}
	
	@GetMapping("/fetchorder/{orderId}")
	public Order fetchByOrderId(@PathVariable Integer orderId)
	{
	  return orderService.fetchByOrderId(orderId);	
	 	
	}
	@GetMapping("/fetchorder")
	public List<Order> fetchByOrderId()
	{
	  return orderService.fetchAllOrder();	
	 	
	}
	
	

}
