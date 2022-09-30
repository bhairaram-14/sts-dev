package com.spring.springjwt.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import javax.validation.ReportAsSingleViolation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.spring.springjwt.entity.Role;
import com.spring.springjwt.entity.User;
import com.spring.springjwt.repository.UserRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepoTest {
	
	@Autowired
	UserRepo userRepo;
	
	//@Test
	public void testCreateUser()
	{
		PasswordEncoder encoder=new BCryptPasswordEncoder();
		String encodedPassswordString =encoder.encode("span2022");
		User user=new User();
             user.setEmail("span@spanidea.com");
             user.setPassword(encodedPassswordString);
    
        String encodedPassswordString2 =encoder.encode("admin2022");
        	     
         	User user2=new User();
            user2.setEmail("admin@spanidea.com");
            user2.setPassword(encodedPassswordString2);
   
             
        user=userRepo.save(user2);
        assertThat(user).isNotNull();
        assertThat(user.getId()).isGreaterThan(0);
	}
	
	//@Test
	public void assignRole()
	{
		Integer userid=1;
		Integer roleId=1;
		
	 Optional<User> user= userRepo.findById(userid);
	 user.get().addRole(new Role(2));
	 user.get().addRole(new Role(3));
	 User updatedUser =userRepo.save(user.get());
	 
	 assertThat(updatedUser.getRoles()).hasSize(1);
		
	}
	

}
