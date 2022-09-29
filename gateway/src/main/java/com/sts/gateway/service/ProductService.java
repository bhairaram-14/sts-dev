package com.sts.gateway.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.sts.gateway.dto.Product;
import com.sts.gateway.dto.ProductResponse;

@org.springframework.stereotype.Service
public class ProductService {


	@Autowired
	private RestTemplate restTemplate;


	public ProductResponse addProduct(Product product)
	{

		String url="http://localhost:6789/product/add";
		//=============================================
		url="lb://PRODUCT-SERVICE/product/add";
		ProductResponse res =new ProductResponse();
		res=restTemplate.postForObject(url, product, ProductResponse.class);
		return res;
	}


	public List<Product> fetchAllProduct()
	{

		String url="http://localhost:6789/product/";
		//==========================================
		url="lb://PRODUCT-SERVICE/product/";
		List<Product> list=restTemplate.getForObject(url,List.class);
		return list; 

	}

	public ProductResponse fetchProductById(Integer id)
	{

		String url="http://localhost:6789/product/"+id;
		//=============================================
		url="lb:PRODUCT-SERVICE/product/"+id;
		
		ProductResponse res=restTemplate.getForObject(url,ProductResponse.class);
		return res; 

	}



	public void delProduct(Integer pid) {


		String url="http://localhost:6789/product/remove/"+pid;
		//=====================================================
		url="lb://PRODUCT-SERVICE/product/remove/"+pid;

		restTemplate.delete(url);

		return;
	}




}