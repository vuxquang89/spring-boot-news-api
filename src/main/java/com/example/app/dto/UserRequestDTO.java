package com.example.app.dto;

import org.hibernate.validator.constraints.Length;

public class UserRequestDTO {

	@Length(min = 2, max = 20)
	private String username;
	
	@Length(min = 2, max = 20)
	private String password;
	
	private DeviceInfo deviceInfo;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public DeviceInfo getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(DeviceInfo deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	
	
	
}
