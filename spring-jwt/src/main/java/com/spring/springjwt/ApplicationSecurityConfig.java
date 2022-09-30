package com.spring.springjwt;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.spring.springjwt.jwt_token.JwtTokwnFilter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(
		prePostEnabled = true,
		securedEnabled = false,
		jsr250Enabled = true
		)
public class ApplicationSecurityConfig  extends WebSecurityConfigurerAdapter{

	@Autowired UserDetailsService user;
    @Autowired JwtTokwnFilter filter;
	
	@Bean
	public PasswordEncoder getEncoder()
	{
		return new BCryptPasswordEncoder();

	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

       // userdetailesService take as an argument
		
		auth.userDetailsService(user);

	}


	@Override
	@Bean 
	public AuthenticationManager authenticationManagerBean() throws Exception {
	
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.exceptionHandling().authenticationEntryPoint((request, response, authException) ->{
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
					authException.getMessage()
					);
		} );
		
		http.authorizeRequests()
		    .antMatchers("/auth/login").permitAll() 
		    .anyRequest().authenticated();  

	  http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);	
		
		//super.configure(http);
	}



}
