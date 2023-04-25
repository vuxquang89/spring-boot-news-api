package com.example.app.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.custom.CustomErrorResponse;
import com.example.app.dto.UserRequestDTO;
import com.example.app.dto.UserResponseDTO;
import com.example.app.entity.TokenEntity;
import com.example.app.entity.UserEntity;
import com.example.app.jwt.JwtTokenUtil;
import com.example.app.service.TokenService;
import com.example.app.service.UserService;
import com.example.app.service.impl.ImplUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.sjavac.Log;

@RestController
@RequestMapping("/api")
public class UserAPI {

	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	JwtTokenUtil jwtUtil;
	
	@Autowired
	private UserService service;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid UserRequestDTO request){
		try {
			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			
			UserEntity user = (UserEntity)authentication.getPrincipal();
			
			String accessToken = jwtUtil.generateAccessToken(user);
			String refreshToken = jwtUtil.generateRefreshToken(user);
			
			revokeAllUserTokens(user);
			saveUserToken(user, accessToken, refreshToken);
			
			UserResponseDTO response = new UserResponseDTO(user.getUsername(), accessToken, refreshToken);
			
			return ResponseEntity.ok(response);
			
		}catch(BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody @Valid UserRequestDTO model){
		UserResponseDTO newUser = service.save(model);
		CustomErrorResponse result = new CustomErrorResponse();
		result.setTimestamp(new Date());
		List<String> ms = new ArrayList<String>();
		System.out.println("username: "+newUser.getUsername());
		
		if(newUser.getUsername() != null) {
			result.setStatus("200");
			ms.add("User registered successfully!");
			
		}else {
			result.setStatus("201");
			ms.add("Username available!");
		}		
		result.setMessages(ms);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/token/refresh")
	public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String header = request.getHeader("Authorization");
		System.out.println("refresh token " + header);
		if(ObjectUtils.isEmpty(header) || !header.startsWith("Example")) {
			throw new RuntimeException("Refresh token is missing");
		}else {
			try {
				String refreshToken = header.split(" ")[1].trim();
				
				if(jwtUtil.validateToken(refreshToken, response) && 
						tokenService.checkRefreshTokenExpired(refreshToken)) {
					String username = jwtUtil.getUserNameFromJwtSubject(refreshToken);
					Optional<UserEntity> user = service.getUser(username);
					String accessToken = jwtUtil.generateAccessToken(user.get());
					UserResponseDTO res = new UserResponseDTO(user.get().getUsername(), accessToken, refreshToken);
					return ResponseEntity.ok(res);
				}else {
					return ResponseEntity.ok().body("You need to login again!");
				}
				
				
			}catch(Exception ex) {
				/*
				Map<String, String> error = new HashMap<String, String>();
				
				error.put("error_message", ex.getMessage());
				new ObjectMapper().writeValue(response.getOutputStream(), error);
				*/
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
			}
			 
		}
	}
	
	private void revokeAllUserTokens(UserEntity user) {
		List<TokenEntity> tokens = tokenService.findAllTokensByUser(user.getId());
		if(tokens.isEmpty())
			return;
		tokens.forEach(t ->{
			t.setExpired(true);
			t.setRevoked(true);
		});
		tokenService.saveAll(tokens);
	}
	
	private void saveUserToken(UserEntity user, String accessToken, String refreshToken) {
		TokenEntity tokenEntity = new TokenEntity();
		tokenEntity.setUser(user);
		tokenEntity.setAccessToken(accessToken);
		tokenEntity.setRefreshToken(refreshToken);
		tokenEntity.setExpired(false);
		tokenEntity.setRevoked(false);
		tokenService.save(tokenEntity);
	}
	
}
