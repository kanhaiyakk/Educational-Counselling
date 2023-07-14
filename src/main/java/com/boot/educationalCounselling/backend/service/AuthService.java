package com.boot.educationalCounselling.backend.service;

import com.boot.educationalCounselling.backend.dto.LoginDto;
import com.boot.educationalCounselling.backend.dto.RegisterDto;


public interface AuthService {
	String login(LoginDto loginDto);
	String register(RegisterDto registerdto);
	
}

