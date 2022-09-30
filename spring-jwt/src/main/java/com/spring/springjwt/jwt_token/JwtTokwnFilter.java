package com.spring.springjwt.jwt_token;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.spring.springjwt.entity.Role;
import com.spring.springjwt.entity.User;

import io.jsonwebtoken.Claims;

@Component
public class JwtTokwnFilter extends OncePerRequestFilter{


	@Autowired private JwtUtility jwtUtility;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		System.out.print("token :"+request.getHeader("Authorization"));

		if(!hasAuthorizationHeader(request))
		{
			filterChain.doFilter(request, response);
			return ;
		}

		String accessToken=getAccessToken(request);
		
		System.out.print("token :"+accessToken);

		if(!jwtUtility.validateToken(accessToken))
		{
			filterChain.doFilter(request, response);
			return ;		  	 
		}

		setAuthenticationContext(accessToken,request);
		filterChain.doFilter(request, response);
		return;
	}

	private void setAuthenticationContext(String accessToken, HttpServletRequest request) {
		
		UserDetails userDetails=getUserdetails(accessToken);

		UsernamePasswordAuthenticationToken authentication=
				new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);

	}

	private UserDetails getUserdetails(String accessToken) {
		
		User userDetails= new User();
		Claims claims=jwtUtility.parseClaims(accessToken);
	    
		String subject=(String) claims.get(Claims.SUBJECT);
	  //SubjectArray[]= jwtUtility.getSubject(accessToken).split(",");
	    String SubjectArray[]=subject.split(",");
	    
	    String claimsroles= (String)claims.get("roles");
	    claimsroles=claimsroles.replace("[", "").replace("]",""); 
	   
	    String roleNames[]=claimsroles.split(",");
	    
	    for(String rolename:roleNames)
	    	userDetails.addRole(new Role(rolename));
	    
	    userDetails.setId(Integer.parseInt(SubjectArray[0]));
		userDetails.setEmail(SubjectArray[1]);

		return userDetails;
	}

	private boolean hasAuthorizationHeader(HttpServletRequest request)
	{
		String header =request.getHeader("Authorization");

		if(ObjectUtils.isEmpty(header) || !header.startsWith("Bearer"))
			return false;

		return true;
	}

	private String getAccessToken(HttpServletRequest request)
	{
		String header=request.getHeader("Authorization");	
		return header.split(" ")[1].trim();	
	}


}
