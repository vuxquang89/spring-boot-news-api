package com.example.app.service.impl;

import java.util.List;

import com.example.app.entity.TokenEntity;

public interface ImplTokenService {

	List<TokenEntity> findAllTokensByUser(Long userId);
	
	TokenEntity findByAccessToken(String accessToken);
	
	TokenEntity findByRefreshToken(String refreshToken);
	
	TokenEntity save(TokenEntity tokenEntity);
	
	void saveAll(List<TokenEntity> tokens);
	
	boolean checkAccessTokenExpired(String token);
	boolean checkRefreshTokenExpired(String token);
}
