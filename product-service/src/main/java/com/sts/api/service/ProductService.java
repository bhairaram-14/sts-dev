package com.sts.api.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sts.api.entity.ProductDto;
import com.sts.api.entity.ProductEntity;
import com.sts.api.entity.ProductResonse;
import com.sts.api.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired(required = true)
	private ProductRepository repo;

	public List<ProductDto> getAllProduct()
	{
		return entityListToDtos(repo.findAll());
		
	}

	public ProductResonse getProduct(Integer pid)
	{
 
		ProductResonse res=new ProductResonse();
		ProductEntity entity = repo.findByProductId(pid);

		if(entity==null)
		{   	res.setMessage("no product available for id : "+ pid);
	        return res;
	   }
		
	      res.setMessage("product with given id : ");
	      res.setDto(entityToDto(entity));  
	      return res;
	}

	public ProductResonse addProduct(ProductDto dto)
	{
		ProductResonse res=new ProductResonse();
            
		
		if(dto.getProductId()!=null)
		{
         res.setMessage("product id must be null for add product or call update api ");	
		 res.setDto(dto);
		 return res;
		}
		
		res.setDto(entityToDto(repo.save(dtoToEntity(dto))));
		res.setMessage("product added with id : "+res.getDto().getProductId());
		
        return res;

	}
	
	public ProductResonse updateProduct(ProductDto dto)
	{
		ProductResonse res=new ProductResonse();

		if(dto.getProductId()==null||repo.findByProductId(dto.getProductId())==null)
		{
			res.setMessage("failed.........provide valid id");	
		    res.setDto(dto);
			return res;
		}

		res.setMessage("updated.....");	
		res.setDto(entityToDto(repo.save(dtoToEntity(dto))));	 	

		return res;

	}


    public String removeProduct(Integer id)
    {
    	
     ProductEntity entity=repo.findByProductId(id); 
     
      if(entity==null)
    	return "no product available for id"+id;
      repo.delete(entity);
       return "product deleted successfullly";
    	   
    	
    }

	
	
	
	
//       .....................================utility method===================.........................	
//       .....................+++++++++++++++++++++++++++++++++++++++++++++++++.........................
    
    
	ProductEntity dtoToEntity(ProductDto dto)
	{
		ProductEntity entity=new ProductEntity();

		entity.setProductId(dto.getProductId());
		entity.setProductName(dto.getProductName());
		entity.setPrice(dto.getPrice());
		entity.setRemainingQuantity(dto.getRemainingQuantity());
		
		return entity;

	}

	ProductDto entityToDto(ProductEntity dto)
	{
		ProductDto entity=new ProductDto();

		entity.setProductId(dto.getProductId());
		entity.setProductName(dto.getProductName());
		entity.setPrice(dto.getPrice());
		entity.setRemainingQuantity(dto.getRemainingQuantity());
		
		return entity;
	}

	
	List<ProductDto>  entityListToDtos(List<ProductEntity> entities)
	{
	 List<ProductDto> dtos=new ArrayList<>();
	 
	 for(ProductEntity entity :entities)
	  dtos.add(entityToDto(entity));	 
     
	 return dtos;
		
	}
	
	
}
