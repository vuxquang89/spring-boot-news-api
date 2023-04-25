package com.example.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.entity.TokenEntity;
import com.example.app.repository.TokenRepository;
import com.example.app.service.impl.ImplTokenService;

@Service
public class TokenService implements ImplTokenService{

	@Autowired
	private TokenRepository repo;
	
	@Override
	public List<TokenEntity> findAllTokensByUser(Long userId) {
		return repo.findAllTokensByUser(userId);
	}
	
	@Override
	public TokenEntity findByAccessToken(String accessToken) {
		return repo.findByAccessToken(accessToken).get();
	}
	
	@Override
	public TokenEntity findByRefreshToken(String refreshToken) {
		return repo.findByRefreshToken(refreshToken).get();
	}
	
	@Override
	public TokenEntity save(TokenEntity tokenEntity) {
		return repo.save(tokenEntity);
	}

	@Override
	public void saveAll(List<TokenEntity> tokens) {
		repo.saveAll(tokens);
	}
	@Override
	public boolean checkAccessTokenExpired(String token) {
		return repo.findByAccessToken(token)
			.map(t -> !t.isExpired() && !t.isRevoked())
			.orElse(false);
	}
	@Override
	public boolean checkRefreshTokenExpired(String token) {
		return repo.findByRefreshToken(token)
				.map(t -> !t.isExpired() && !t.isRevoked())
				.orElse(false);
	}
}

