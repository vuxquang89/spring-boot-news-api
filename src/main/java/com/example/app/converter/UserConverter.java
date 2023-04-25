package com.example.app.converter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.app.dto.UserRequestDTO;
import com.example.app.dto.UserResponseDTO;
import com.example.app.entity.UserEntity;

@Component
public class UserConverter {

	public UserEntity toEntity(UserRequestDTO userRequest) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
		UserEntity user = new UserEntity();
		user.setUsername(userRequest.getUsername());
		user.setPassword(encodedPassword);
		return user;
	}
	
	public UserResponseDTO toResponse(UserEntity user) {
		UserResponseDTO userResponse = new UserResponseDTO();
		userResponse.setUsername(user.getUsername());
		return userResponse;
	}
}
