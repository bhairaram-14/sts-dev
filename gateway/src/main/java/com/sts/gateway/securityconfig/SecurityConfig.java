package com.sts.gateway.securityconfig;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import com.sts.gateway.jasonwebtoken.JwtFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

	
	@Autowired JwtFilter filter;
	@Autowired UserDetailsService user;
	
	
	@Bean
	public PasswordEncoder getEncoder()
	{
		return new BCryptPasswordEncoder();

	}

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
	
		auth.userDetailsService(user);
	
		
	}
	

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		return super.authenticationManagerBean();
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.csrf().disable();
		System.out.print("hello");
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		

		http.authorizeRequests()
		    .antMatchers("/gate/login").permitAll() 
		    .anyRequest().authenticated();
		http.exceptionHandling().authenticationEntryPoint((request, response, authException) ->{
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
					authException.getMessage()
					);
		} );
	
		
	    http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		
		//super.configure(http);
	}

	
	
}
