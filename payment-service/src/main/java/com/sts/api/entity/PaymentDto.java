package com.sts.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {

	private Integer paymentId;
	private String paymentStatus;
	private String transactionId;
	private Integer orderId;
	private double amount;
	private boolean isPaymentSuccesfull;
}
