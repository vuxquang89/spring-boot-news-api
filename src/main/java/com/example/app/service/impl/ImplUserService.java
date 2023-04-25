package com.example.app.service.impl;

import java.util.Optional;

import com.example.app.dto.UserRequestDTO;
import com.example.app.dto.UserResponseDTO;
import com.example.app.entity.UserEntity;

public interface ImplUserService {

	Optional<UserEntity> getUser(String username);
	
	UserResponseDTO save(UserRequestDTO user);
}
