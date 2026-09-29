package com.deepak.UserService.Service;

import com.deepak.UserService.DTO.AdminRegisterRequest;
import com.deepak.UserService.DTO.AdminResponse;
import com.deepak.UserService.DTO.LoginResponse;
import com.deepak.UserService.DTO.RegisterRequest;
import com.deepak.UserService.DTO.UserResponse;

public interface AuthService {

	// for registeration 
	public String register(RegisterRequest request);
	
	// Note : ALLWAYS SEND DTO AS RESPONSE ..
	// to get user by id  
	public UserResponse getUserById(Long uid);
	
	
	// TO LOGIN
	public LoginResponse login(String email, String password);
	
	// Note : ALLWAYS SEND DTO AS RESPONSE ..
	// TO RESPONSE TO USER
	public AdminResponse getUserDetailsById(Long uid);
	
	
	public String createAdmin(AdminRegisterRequest request);
}
