package com.spring.springjwt.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.spring.springjwt.entity.Role;
import com.spring.springjwt.repository.RoleRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepoTest {

	@Autowired
	RoleRepository repo;
	//@Test
	public void  testCreateRole()
	{
	  
	 Role admin    = new Role("ROLE_ADMIN");
	 Role editor   = new Role("ROLE_EDITOR");
	 Role customer = new Role("ROLE_CUSTOMER");
	 
	 repo.saveAll(List.of(admin,editor,customer));
	 
	 long noOfRoles=repo.count();
	 assertEquals(3, noOfRoles);
	 
	}
	
	//@Test
	public void listRoles()
	{
		List<Role> roles=repo.findAll();;
	   assertThat(roles.size()).isGreaterThan(0);
	
	  roles.forEach(System.out::println);   
	}
	
	
}
