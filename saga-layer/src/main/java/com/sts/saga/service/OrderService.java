package com.sts.saga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sts.saga.dtos.Order;
import com.sts.saga.dtos.OrderResponse;

@Service
public class OrderService {

	 @Autowired
	private RestTemplate restTemplate;
	 

	
	public Order createOrder(Order order)
	{
		
		String url="lb://ORDER-SERVICE/order/createOrder";
		//String url="http://localhost:6791/order/createOrder";
		return restTemplate.postForObject(url,order, Order.class);

		
	}
  
	
	public Order fetchByOrderId(Integer orderId)
	{
		String url="lb://ORDER-SERVICE/order/"+orderId;
		
		Order res=restTemplate.getForObject(url,Order.class);

        return res;
		
	}

	public List<Order> fetchAllOrder()
	{
		String url="lb://ORDER-SERVICE/order/";
		
		List<Order> res=restTemplate.getForObject(url, List.class);

        return res;
		
	}	
	
	
	
	

}
