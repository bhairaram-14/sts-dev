package com.sts.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sts.api.entity.PaymentEntity;
@Repository
public interface PaymentRepo extends JpaRepository<PaymentEntity, Integer>
{
  public List<PaymentEntity> findByOrderId(Integer oid);
  //public PaymentEntity 
    PaymentEntity  findByPaymentId(Integer pid);
}

