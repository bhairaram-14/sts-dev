package com.sts.gateway.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

	private Integer orderId;
	private String name;
	private int quantity;
	private double price;	
	private String orderStatus;
}
