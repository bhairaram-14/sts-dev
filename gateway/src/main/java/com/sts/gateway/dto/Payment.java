package com.sts.gateway.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Payment {

	private Integer paymentId;
	private String paymentStatus;
	private String transactionId;
	private Integer orderId;
	private double amount;
	private boolean isPaymentSuccesfull;
}
