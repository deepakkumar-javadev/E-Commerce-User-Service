package com.deepak.UserService.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepak.UserService.DTO.AdminRegisterRequest;
import com.deepak.UserService.DTO.AdminResponse;
import com.deepak.UserService.DTO.LoginRequest;
import com.deepak.UserService.DTO.LoginResponse;
import com.deepak.UserService.DTO.RegisterRequest;
import com.deepak.UserService.DTO.UserResponse;
import com.deepak.UserService.Service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	private final AuthService service;

	public AuthController(AuthService service) {
		this.service = service;
	}

	
	// ## 1.Registration

	@PostMapping("/register")

	public String register(@RequestBody RegisterRequest request) {

		logger.info("Register api called successfully..");

		return service.register(request);
	}

	//# 2. LOGIN API
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> Login(@RequestBody LoginRequest req) {
		logger.info("login api called succesfully...");
		LoginResponse response = service.login(req.getEmail(), req.getPassword());

		return ResponseEntity.ok(response);
	}

	// # 3. Get USER BASIC DETAILS

	
	@GetMapping("/getuser/{uid}")
	public ResponseEntity<UserResponse> getUser(@PathVariable Long uid) {

		logger.info("logger.info : getUser API Called successfully...");
		UserResponse user = service.getUserById(uid);

		return ResponseEntity.ok(user);
	}

	//#4. GET USpER BASIC DETIALS + ROLE -> AdminResponse

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/UserDetails/{uid}")
	public ResponseEntity<AdminResponse> Userinfo(@PathVariable Long uid) {

		logger.info("userInfo api called successfully...");

		AdminResponse response = service.getUserDetailsById(uid);

		return ResponseEntity.ok(response);
	}
	
	// create admin api 
	
	@PostMapping("/registerAdmin")
	public ResponseEntity<String> createAdmin(
	        @Valid @RequestBody AdminRegisterRequest request) {
		logger.info("AdminRegister  successfully...");
	    return ResponseEntity.ok(service.createAdmin(request));
	}
}
