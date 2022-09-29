package com.sts.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sts.api.entity.ProductEntity;


@Repository

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

	ProductEntity findByProductId(Integer bigInteger);
	 
	
}
