package com.sts.gateway.jasonwebtoken;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sts.gateway.entity.User;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	JwtUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException
	       {
		    if(!hasAuthorizationHeader(request))
		    {
		    	filterChain.doFilter(request, response);
		    	return;
		    }	 
		   
		    String accessToken=getAccessToken(request);
		    
		    if(!jwtUtil.validateToken(accessToken))
		    {
		     filterChain.doFilter(request, response);	
		     return ;
		    }
		    
		    setAuthenticationContext(accessToken,request);
		    filterChain.doFilter(request, response);
		    return;
		     
		
	      }

	

	private void setAuthenticationContext(String accessToken, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		UserDetails userDetails=getUserDetails(accessToken);
		
		UsernamePasswordAuthenticationToken authentication=
				new UsernamePasswordAuthenticationToken(userDetails ,null,null);
         	authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
         	SecurityContextHolder.getContext().setAuthentication(authentication);
	}



	private UserDetails getUserDetails(String accessToken) {
	   
		User userDetails=new User();
		
		String subjectArray[]=jwtUtil.getSubject(accessToken).split(",");
		System.out.print("hello");
		userDetails.setId(Integer.parseInt(subjectArray[0]));
		userDetails.setEmail(subjectArray[1]);
		
		return userDetails;
	}



	private boolean hasAuthorizationHeader(HttpServletRequest request) {
	
		String header =request.getHeader("Authorization");
		if(ObjectUtils.isEmpty(header)||!header.startsWith("Bearer"))
	     	return false;
	    return true;
	}
	
	
	
	private String getAccessToken(HttpServletRequest request) {
	
		return request.getHeader("Authorization").split(" ")[1].trim();
	}

  	
	
}
