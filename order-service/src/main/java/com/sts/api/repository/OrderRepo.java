package com.sts.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sts.api.entity.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

	Order findByOrderId(int id);

}
