package com.sts.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sts.api.entity.PaymentDto;
import com.sts.api.entity.PaymentEntity;
import com.sts.api.entity.PaymentResponse;
import com.sts.api.repository.PaymentRepo;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepo repo;

	public PaymentDto doPayment(PaymentDto dto)
	{
		PaymentEntity entity=	dtoToEntity(dto);
		entity.setPaymentStatus(paymentProcess());
		entity.setTransactionId(UUID.randomUUID().toString());
		if(entity.getPaymentStatus().equals("done"))
			entity.setPaymentSuccesfull(true);
		else
			entity.setPaymentSuccesfull(false);


		entity =repo.save(entity);	

		return entityToDTo(entity);
	}


	public List<PaymentDto> FetchByOid(int oid)
	{
		return entityListToDtos(repo.findByOrderId(oid));	
	}


	public PaymentResponse FetchByPid(int pid)
	{
		PaymentResponse res=new PaymentResponse();
		
       PaymentDto dto= entityToDTo(repo.findByPaymentId(pid));	
	   if(dto==null)
	   {
		   res.setMessage("no paymnets for given id "+pid);
	       return res;
	   }
	   
		   res.setMessage("payment with id : "+pid);
	       res.setDto(dto);
          return res;
	}


	//=============================================================================================
	//	utilities
	//=============================================================================================	

	public String paymentProcess()
	{
		return new Random().nextBoolean()?"done":"payment-failed";
	}

	public PaymentDto entityToDTo(PaymentEntity entity)
	{
		if(entity==null) return null;
		PaymentDto dto=new PaymentDto();
		dto.setAmount(entity.getAmount());
		dto.setOrderId(entity.getOrderId());
		dto.setPaymentStatus(entity.getPaymentStatus());
		dto.setPaymentId(entity.getPaymentId());
		dto.setTransactionId(entity.getTransactionId());
		dto.setPaymentSuccesfull(entity.isPaymentSuccesfull());
		return dto; 
	}

	public PaymentEntity dtoToEntity(PaymentDto entity)
	{

		if(entity==null)return null;
		
		PaymentEntity dto=new PaymentEntity();
		dto.setAmount(entity.getAmount());
		dto.setOrderId(entity.getOrderId());
		dto.setPaymentStatus(entity.getPaymentStatus());
		dto.setPaymentId(entity.getPaymentId());
		dto.setTransactionId(entity.getTransactionId());
		dto.setPaymentSuccesfull(entity.isPaymentSuccesfull());
		return dto;  
	}


	public List<PaymentDto> entityListToDtos(List<PaymentEntity> entities)
	{
		List<PaymentDto> dtos=new ArrayList<>();
		for(PaymentEntity entity:entities)
		{
			dtos.add(entityToDTo(entity));
		}
		return dtos;


	}


	public List<PaymentDto> FetchAll() 
	{
		return entityListToDtos(repo.findAll());


	}




}
