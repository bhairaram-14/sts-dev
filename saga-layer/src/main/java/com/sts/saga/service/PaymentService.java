package com.sts.saga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sts.saga.dtos.Payment;
import com.sts.saga.dtos.PaymentResponse;

@Service
public class PaymentService {
	
	@Autowired
	private RestTemplate restTemplate;
	

	public PaymentResponse pay(Payment payment)
	{

		String url="http://localhost:6790/payment/pay";
	      //===========================================
		url="lb://PAYMENT-SERVICE/payment/pay";
		PaymentResponse res=restTemplate.postForObject(url,payment, PaymentResponse.class);

		return res; 
	}

	public PaymentResponse fetchByPaymentId(Integer pid)
	{

		String url="http://localhost:6790/payment/"+pid;
		//=========================================
		url="lb://PAYMENT-SERVICE/payment/"+pid;
	
		PaymentResponse res=restTemplate.getForObject(url, PaymentResponse.class);

		return res; 
	}


	public List<Payment> fetchPaymentByOrderId(Integer oId)
	{

		
		String url="http://localhost:6790/payment/oid/"+oId;
	    //after registry wuth eureka 
		url="lb://PAYMENT-SERVICE/payment/oid/"+oId;
		List<Payment> res=(List<Payment>)restTemplate.getForObject(url, List.class);

		return res; 
	}
	
	public List<Payment> fetchAll()
	{
		

		String url="lb://PAYMENT-SERVICE/payment/";
		//url="http://localhost:6790/payment/";
	
		List<Payment> res=(List<Payment>)restTemplate.getForObject(url, List.class);

		return res; 
	}
	
}
