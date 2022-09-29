package com.sts.gateway.jasonwebtoken;

import java.util.Date;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder;
import com.sts.gateway.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;


@Component
public class JwtUtil {
	
	private static final  long SESSION_TIME=(long) 24*60*60*1000;
	private static final  Logger LOGGER = org.slf4j.LoggerFactory.getLogger(JwtUtil.class);
	
	@Value("${application.jwt.secretKey}")
	String secretKey;
	
	public String generateAccessToken(User user)
	{
	   String token= Jwts.builder()
			   .setSubject(user.getId()+","+user.getEmail())
			   .setIssuer("gatewaysecurity")
			   .setIssuedAt(new Date())
			   .setExpiration(new Date(System.currentTimeMillis()+SESSION_TIME))
			   .signWith(SignatureAlgorithm.HS512, secretKey)
		       .compact();
		return token;
	}
	
	public boolean validateToken(String token)
	{
	  try{
		 Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
		 return true;
	  }catch(ExpiredJwtException e)
	  {
		 LOGGER.error("token expired",e);
		 
	  }catch(IllegalArgumentException e)
	  {
		  LOGGER.error("token format is not correct",e);
	  }catch(MalformedJwtException e)
	  {
		 LOGGER.error("JWT is invalid" ,e); 
	  }catch(UnsupportedJwtException e)
	  {
		  LOGGER.error("JWT is not support",e);
	  }catch(SignatureException e)
	  {
		 LOGGER.error("signature validation failed"); 
	  }
	  return false;}
	

	
	public String getSubject(String token)
	{
		return parseClaim(token).getSubject();
	}
	
	public Claims parseClaim(String token)
	{
		System.out.print(secretKey);
	 return Jwts.parser()
			.setSigningKey(secretKey)
			.parseClaimsJws(token)
			.getBody();
			
	}
	
}
