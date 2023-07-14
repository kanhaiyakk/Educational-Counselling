package com.boot.educationalCounselling.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class LoginDto {
	
	@Email(message = "enter vaild email")
	@NotEmpty(message ="email field is mandatory")
	private String email;
	
	@NotEmpty(message ="password field is mandatory")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public LoginDto() {
	}
}
