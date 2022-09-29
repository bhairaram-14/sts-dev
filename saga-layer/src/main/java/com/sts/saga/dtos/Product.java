package com.sts.saga.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Integer productId;
    private String productName;
	private Integer remainingQuantity;
	private Double price;

}
