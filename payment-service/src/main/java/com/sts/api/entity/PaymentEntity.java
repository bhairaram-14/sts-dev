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
@Table(name="payment_Table")
@NoArgsConstructor
@AllArgsConstructor
@Data
public  class PaymentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer paymentId;
	@Column
	private String paymentStatus;
	@Column
	private String transactionId;
	@Column
	private Integer orderId;
	@Column
	private double amount;
	
	@Column
	private boolean isPaymentSuccesfull;
	
	
}
	

