package com.example.app.dto;

public class UserResponseDTO {

	private String username;
	private String accessToken;
	private String refreshToken;
	
	public UserResponseDTO() {}
	
	public UserResponseDTO(String username, String accessToken, String refreshToken) {
		this.username = username;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
}
