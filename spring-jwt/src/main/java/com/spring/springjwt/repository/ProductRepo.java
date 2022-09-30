package com.spring.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.springjwt.entity.Product;
@Repository  
public interface ProductRepo extends JpaRepository<Product, Integer> {

}
