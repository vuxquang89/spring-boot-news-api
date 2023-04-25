package com.example.app.test.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.app.entity.RoleEntity;
import com.example.app.repository.RoleReponsitory;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {

	@Autowired
	private RoleReponsitory repo;
	
	@Test
	public void testCreateRole() {
		RoleEntity admin = new RoleEntity("ROLE_ADMIN");
		RoleEntity editor = new RoleEntity("ROLE_EDITOR");
		RoleEntity user = new RoleEntity("ROLE_USER");
		
		repo.saveAll(List.of(admin, editor, user));
		long count = repo.count();
		
		assertEquals(3, count);
	}
	
	@Test
	public void testListRoles() {
		List<RoleEntity> listRoles = repo.findAll();
		assertThat(listRoles.size()).isGreaterThan(0);
		
		listRoles.forEach(System.out::println);
	}
	
}
