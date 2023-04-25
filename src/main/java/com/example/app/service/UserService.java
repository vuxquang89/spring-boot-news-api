package com.example.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.converter.UserConverter;
import com.example.app.dto.UserRequestDTO;
import com.example.app.dto.UserResponseDTO;
import com.example.app.entity.RoleEntity;
import com.example.app.entity.UserEntity;
import com.example.app.repository.UserRepository;
import com.example.app.service.impl.ImplUserService;

@Service
public class UserService implements ImplUserService{

	@Autowired
	private UserRepository response;
	
	@Autowired
	private UserConverter userConverter;
	
	@Override
	public Optional<UserEntity> getUser(String username) {
		return response.findByUsername(username);
	}
	
	@Override
	public UserResponseDTO save(UserRequestDTO user) {
		UserResponseDTO saveUser = new UserResponseDTO();
		
		if(!response.findByUsername(user.getUsername()).isPresent()) {
			System.out.println("Create new User");
			UserEntity newUser = userConverter.toEntity(user);
			newUser.addRole(new RoleEntity((long)3));
			saveUser = userConverter.toResponse(response.save(newUser));
		}
		return saveUser;
	}
}
