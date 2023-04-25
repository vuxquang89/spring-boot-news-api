package com.example.app.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.example.app.entity.TokenEntity;

@Service
public class LogoutService implements LogoutHandler{

	@Autowired
	private TokenService tokenService;
	
	@Override
	public void logout(HttpServletRequest request, 
			HttpServletResponse response, 
			Authentication authentication) {
		String header = request.getHeader("Authorization");
		System.out.println("Logout Authorization header : " + header);
		
		String jwt;
		
		if(ObjectUtils.isEmpty(header) || !header.startsWith("Example")) {
			return;
		}
		jwt = header.split(" ")[1].trim();
		
		TokenEntity storedToken = tokenService.findByAccessToken(jwt);
		
		if(storedToken != null) {
			storedToken.setExpired(true);
			storedToken.setRevoked(true);
			tokenService.save(storedToken);
		}
	}
}
