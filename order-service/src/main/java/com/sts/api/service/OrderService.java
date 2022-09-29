package com.sts.api.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sts.api.common.OrderDto;
import com.sts.api.common.PaymentDto;
import com.sts.api.common.Request;
import com.sts.api.common.Response;
import com.sts.api.entity.Order;
import com.sts.api.repository.OrderRepo;

import utilityser.ConverterService;

@Service
public class OrderService {
	@Autowired
	private OrderRepo repo;

	

	public OrderDto createOrder(OrderDto order)
	{

        order.setOrderStatus("order-requested");
        
        Order entity=ConverterService.DtoToEntity(order);
        entity=repo.save(entity);
        
		order= ConverterService.entityToDtoOrder(entity);
		
		
		return order;
	}

	public OrderDto placeOrder(OrderDto order)
	{

        order.setOrderStatus("order-placed");
        
		order= ConverterService.entityToDtoOrder(repo.save(ConverterService.DtoToEntity(order)));
		return order;
	} 


	public OrderDto deleverOrder(OrderDto order)
	{

        order.setOrderStatus("order-delevered");
        
		order= ConverterService.entityToDtoOrder(repo.save(ConverterService.DtoToEntity(order)));
		return order;
	} 
	
	public List<Order> getAllOrder()
	{
		return repo.findAll();
	}

	public Order getById(int id)
	{
		return repo.findByOrderId(id); 
	}

	public String cancelOrder(int id) {
		// TODO Auto-generated method stub
		repo.delete(repo.findByOrderId(id));
		return "oreder canceled";
	}


	/* * 
	 * public String cancelOrder(int id) { Order entity=repo.findByOid(id);
	 * 
	 * if(entity!=null) { repo.delete(entity); return "succesfully deleted"; }
	 * return "No order regestered with this id";
	 * 
	 * }
	 */}
