package com.deepak.UserService.Service;

import com.deepak.UserService.DTO.RegisterRequest;
import com.deepak.UserService.DTO.UserResponse;
import com.deepak.UserService.Entity.User;

public interface AuthService {

	// for registeration 
	public String register(RegisterRequest request);
	
	// Note : ALLWAYS SEND DTO AS RESPONSE ..
	// to get user by id  
	public UserResponse getUserById(Integer uid);
	
	
	// TO LOGIN
	public String login(String email, String password);
	
	// Note : ALLWAYS SEND DTO AS RESPONSE ..
	// TO RESPONSE TO USER
	public UserResponse getUserDetailsById(Integer uid);
}
