package com.deepak.UserService.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.deepak.UserService.DTO.RegisterRequest;
import com.deepak.UserService.DTO.UserResponse;
import com.deepak.UserService.Entity.User;
import com.deepak.UserService.Repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	// to generate log message into destination
	private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepo;

	@Override
	public String register(RegisterRequest request) {

		// for store that dto data we need repository class object & repository class //
		// need entity to save data // [DTO to entity conversion for db operation ]

		logger.info("register method called ");
		User user = new User();// ENTITY OBJECT

		// set name
		user.setName(request.getName()); // get name from dto and set to entity class object
		// set email
		user.setEmail(request.getEmail()); // get EMAIL from dto and set to entity class object
		// set password
		user.setPassword(passwordEncoder.encode(request.getPassword()));

		// external data

		user.setUserRole("Customer");

		// save user data in table
		userRepo.save(user);

		logger.info("user Registered Succesfully and details store in db...");
		return "User Registered successfully..";
	}

	// logic to get user by id

	@Override
	public UserResponse getUserById(Integer uid) {

		User user = userRepo.findById(uid).orElseThrow(() -> {
			logger.error("User NOT Found" + uid);
			return new RuntimeException("User not FOUND WITH ID :" + uid);
		});
		
		//dto object and bind entity to dto data
		UserResponse userRes = new UserResponse();
		userRes.setUid(user.getUid());
		userRes.setName(user.getEmail());
		userRes.setEmail(user.getEmail());
		userRes.setRole(user.getUserRole());
		
		logger.info("user found successfully ..");
		return userRes;

	}

	// LOGIN LOGIC

	@Override
	public String login(String email, String password) {

		// get user by name
		User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User NOT FOUND"));

		if (user.getEmail().equals(email) && passwordEncoder.matches(password, user.getPassword())) {
			logger.info("Login successful...");
			return "Login Success";
		}

		logger.warn("Invalid Password for email " + email);
		return "Invalid Credentials";
	}

	// for response LOGIC

	@Override
	public UserResponse getUserDetailsById(Integer uid) {

		// get USER from entity table
		User user = userRepo.findById(uid).orElseThrow(() -> {
			logger.error("user not found");
			return new RuntimeException("User not found");
		});

		// bind entity user to Dto
		UserResponse response = new UserResponse();
		response.setUid(user.getUid());
		response.setName(user.getName());
		response.setEmail(user.getEmail());
		response.setRole(user.getUserRole());

		// send data with dto
		logger.info("user Info send succesfully ..");
		return response;
	}

}
