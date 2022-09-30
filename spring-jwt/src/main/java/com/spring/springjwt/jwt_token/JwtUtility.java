	package com.spring.springjwt.jwt_token;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.stereotype.Component;

import com.spring.springjwt.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtility {

	private static final Long DURATION_TIME =(long) (24*60*60*1000);
    private static final Logger LOGGER=LoggerFactory.getLogger(JwtUtility.class);
    
	@Value("${app.jwt.seccret}")
	private String secretKey;
	


	public String generateAccessToken(User user)
	{

		//System.out.print("\n\n\n\nh========================ellooo======================\n\n\n\n");
		String accessToken=Jwts.builder()
				.setSubject(user.getId()+","+user.getEmail())	
				.claim("roles", user.getRoles().toString())
				.setIssuer("securitydeveloper")
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+DURATION_TIME))
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
		return accessToken;
	}
	
	public boolean validateToken(String token)
	{
	  try {
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
	  return false;
	}
	
	public String getSubject(String token)
	{
		
		return parseClaims(token).getSubject();
	}
	
	public Claims parseClaims(String token)
	{
		return Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody();
				
	}


}
