package com.sts.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

	
    private Integer productId;
    private String productName;
	private Integer remainingQuantity;
	private Double price;
	
	




}
