package com.example.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.app.entity.TokenEntity;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long>{

	@Query(value = "SELECT * FROM token t INNER JOIN user u on t.user_id = u.id "
			+ "WHERE u.id = :userId and (t.expired = false or t.revoked = false)",
			nativeQuery = true)
	List<TokenEntity> findAllTokensByUser(Long userId);
	
	Optional<TokenEntity> findByAccessToken(String accessToken);
	
	Optional<TokenEntity> findByRefreshToken(String refreshToken);
}
