package com.deepak.UserService.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepak.UserService.DTO.LoginRequest;
import com.deepak.UserService.DTO.RegisterRequest;
import com.deepak.UserService.DTO.UserResponse;
import com.deepak.UserService.DTO.UserResponse;
import com.deepak.UserService.Entity.User;
import com.deepak.UserService.Service.AuthService;
import java.io.File;


@RestController
@RequestMapping("/auth") // define base url for all apis
public class AuthController {

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	
	private final AuthService service;

	public AuthController(AuthService service) {
		this.service = service;
	}

	// ## 1.Registration

	@PostMapping("/register") // note : RegisterRequest is binding/dto class spring does the binding //
								// automaticlly before controller method run
	public String register(@RequestBody RegisterRequest request) {

		logger.info("Register api called successfully..");
		// that data transfer to service layer for processing
		// service> take data > convert into entity> store in repository> return success
		// msg..
		return service.register(request);
	}

	// 2. get UserDetails by id
	@GetMapping("/getuser/{uid}")
	public ResponseEntity<UserResponse> getUser(@PathVariable Integer uid) {

		logger.info("logger.info : getUser API Called successfully...");
		UserResponse user = service.getUserById(uid);

		return ResponseEntity.ok(user);
	}

	@PostMapping("/login") // capture incoming data from user request url and bind with the dto object
	public String Login(@RequestBody LoginRequest req) {
		logger.info("login api called succesfully...");
		service.login(req.getEmail(), req.getPassword());

		return "login successfully";
	}

	@GetMapping("/UserDetails/{uid}")
	public String Userinfo(@PathVariable Integer uid) {

		logger.info("userInfo api called succesfully...");
		service.getUserDetailsById(uid);
		return " UserInfo send SUCCUSSFULLY .......for userId " + uid;
	}

}
