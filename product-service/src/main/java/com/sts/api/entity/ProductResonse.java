package com.sts.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResonse {

 
  private String message;
  private ProductDto dto;
  
	
}
