package com.sts.api.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentResponse {

	private String message;
	private PaymentDto dto;
	

}
