package com.sts.gateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.netflix.discovery.converters.Auto;
import com.sts.gateway.dto.AuthReq;
import com.sts.gateway.dto.AuthRes;
import com.sts.gateway.dto.Order;
import com.sts.gateway.dto.Payment;
import com.sts.gateway.dto.PaymentResponse;
import com.sts.gateway.dto.Product;
import com.sts.gateway.dto.ProductResponse;
import com.sts.gateway.entity.User;
import com.sts.gateway.jasonwebtoken.JwtUtil;
import com.sts.gateway.service.OrderService;
import com.sts.gateway.service.PaymentService;
import com.sts.gateway.service.ProductService;

@RestController
@RequestMapping("/gate")
public class Controller {

	@Autowired AuthenticationManager authenticationManager;
    @Autowired JwtUtil jwtUtil;
    
    
	
	@Autowired 
	private ProductService productService;

	@Autowired 
	private PaymentService paymentservice;

	@Autowired 
	private OrderService orderService;

	
//===========================test====================	
	
	
@PostMapping("/login")
public ResponseEntity<?> matchcredential(@RequestBody AuthReq authReq)
{
	String line = "";
  try{
	  line = "1";
	  Authentication authentication =authenticationManager.authenticate
				( new UsernamePasswordAuthenticationToken
						   (
								authReq.getUsername(), authReq.getPassword()
						   )
						);
			  
	  line = "2";
	  User user=(User)authentication.getPrincipal();
		String accessToken= jwtUtil.generateAccessToken(user);
		line = "3";
	    AuthRes authRes=new AuthRes(user.getEmail(), accessToken);
	    return ResponseEntity.ok(authRes);
		
  }catch(BadCredentialsException e)
  {
	  String error= "not authorized....wrong creddentials" + "||" + line + "||" + e.getMessage();
      return ResponseEntity.ok(error);
  }
}
	
	
	@GetMapping("/") 
	public ResponseEntity<?> test() 
	{
		int x;
		x=5;

		System.out.print("hi dubugger");
		x=9;
		System.out.print("hi dubugger");
		return     ResponseEntity.ok("WELCOME --------MAN.....!!");
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
	public ResponseEntity<Order> fetchByOrderId(@PathVariable Integer orderId)
	{
	  Order order =orderService.fetchByOrderId(orderId);	
	  return ResponseEntity.ok(order);
	  
	  
	}
	@GetMapping("/fetchorder")
	public List<Order> fetchByOrderId()
	{
	  return orderService.fetchAllOrder();	
	 	
	}
	
	

}
