package com.sts.api.common;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentDto {

	private Long paymentId;
	private String paymentStatus;
	private String transactionId;
	private long orderId;
	private double amount;

}
