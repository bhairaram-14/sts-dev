package com.spring.springjwt.entity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="user_table")
@AllArgsConstructor
@NoArgsConstructor

public class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer id;
	@Column(nullable = false)
	private String email; 
	@Column(nullable = false)
	private String password;
	
	@ManyToMany 
	@JoinTable(
			  name = "user_roles_table",
			  joinColumns = @JoinColumn(name="user_Id"),
			  inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<Role> roles=new HashSet<>();
	
	
	
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		
		List<SimpleGrantedAuthority> authorities= new ArrayList<>();
	    
		for(Role role:roles)
		  authorities.add(new SimpleGrantedAuthority(role.getRole()));
	
		 return authorities ;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public void addRole(Role role )
	{
	  this.roles.add(role);	
	}
	
}
