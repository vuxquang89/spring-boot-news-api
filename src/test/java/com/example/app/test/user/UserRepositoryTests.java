package com.example.app.test.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.example.app.entity.RoleEntity;
import com.example.app.entity.UserEntity;
import com.example.app.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	@Autowired
	UserRepository userRepository;
	
	@Test
	public void testCreateUser() {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String rawPassword = "123456";
		String ancodedPassword = passwordEncoder.encode(rawPassword);
		
		UserEntity newUser = new UserEntity();
		newUser.setUsername("vu4");
		newUser.setPassword(ancodedPassword);
		
		UserEntity saveUser = userRepository.save(newUser);
		
		assertThat(saveUser).isNotNull();
		assertThat(saveUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testAddRoleToUser() {
		Long userId = (long)1;
		//Long roleId = (long)1;
		UserEntity user = userRepository.findById(userId).get();
		//user.addRole(new RoleEntity((long)2));
		user.addRole(new RoleEntity((long)1));
		
		UserEntity userUpdate = userRepository.save(user);
		assertThat(userUpdate.getRoles()).hasSize(1);
	}
}
