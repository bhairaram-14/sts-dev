package com.sts.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sts.api.entity.PaymentDto;
import com.sts.api.entity.PaymentEntity;
import com.sts.api.entity.PaymentResponse;
import com.sts.api.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	 @Autowired
	 private PaymentService service;
	 
	 @GetMapping("/test")
	 public String test()
	 {
		 return "payment controller";
	 }
	 
	 @PostMapping("/pay")
	 public PaymentResponse doPayment(@RequestBody PaymentDto payment)
	 {
		 PaymentResponse res=new PaymentResponse();
	     PaymentDto dto= service.doPayment(payment);	 
	     
	     res.setDto(dto);
	     
	     if(dto.isPaymentSuccesfull())
			res.setMessage("payment failed...........");
		else
			res.setMessage("payment succesfull......");
	     
		return res;
	 }
	 
	 @GetMapping("/oid/{oid}")
	 public List<PaymentDto> fetchByOid(@PathVariable int oid)
	 {
		return service.FetchByOid(oid); 
	 }
	 
	 @GetMapping("/{pid}")
	 public PaymentResponse fetchByPid(@PathVariable int pid)
	 {
		return service.FetchByPid(pid); 
	 }
	 
	 @GetMapping("/")
	 public List<PaymentDto> fetchAll()
	 {
		return service.FetchAll(); 
	 }
	
	 
}
