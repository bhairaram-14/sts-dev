package com.sts.api.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

 private OrderDto order;
 private String transactionId;
 private String messagge;
 private boolean isOrderCreated;
 
	
}
