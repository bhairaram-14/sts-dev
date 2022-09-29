package com.sts.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="order_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    
	@Id
	@Column(name="order_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	
	@Column
	private String name;

	@Column
	private int quantity;

	@Column
	private double price;
	
	@Column
	private String orderStatus;
}
