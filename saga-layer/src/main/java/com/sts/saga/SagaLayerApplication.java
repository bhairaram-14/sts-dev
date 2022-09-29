package com.sts.saga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class SagaLayerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SagaLayerApplication.class, args);
	}
	@Bean
   @LoadBalanced
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}
	
	
	

}
