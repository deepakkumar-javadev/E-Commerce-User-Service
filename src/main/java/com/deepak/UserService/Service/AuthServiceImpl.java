package com.deepak.UserService.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.deepak.UserService.DTO.AdminRegisterRequest;
import com.deepak.UserService.DTO.AdminResponse;
import com.deepak.UserService.DTO.LoginResponse;
import com.deepak.UserService.DTO.RegisterRequest;
import com.deepak.UserService.DTO.UserResponse;
import com.deepak.UserService.Entity.User;
import com.deepak.UserService.Entity.UserRole;
import com.deepak.UserService.Repository.UserRepository;
import com.deepak.UserService.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	// to generate log message into destination
	private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepo;

	@Override
	public String register(RegisterRequest request) {

		logger.info("register method called");

		// Check if email already exists
		if (userRepo.existsByEmail(request.getEmail())) {
			logger.warn("Registration failed. Email already registered: {}", request.getEmail());

			return "Email already registered";
		}

		User user = new User();

		user.setName(request.getName());
		user.setEmail(request.getEmail());

		user.setPassword(passwordEncoder.encode(request.getPassword()));

		// Always CUSTOMER during normal registration
		user.setUserRole(UserRole.CUSTOMER);

		userRepo.save(user);

		logger.info("User registered successfully and details stored in DB");

		return "User Registered successfully..";
	}

	// logic to get user by id

	@Override
	public UserResponse getUserById(Long uid) {

		User user = userRepo.findById(uid).orElseThrow(() -> {
			logger.error("User NOT Found" + uid);
			return new RuntimeException("User not FOUND WITH ID :" + uid);
		});

		// dto object and bind entity to dto data
		UserResponse userRes = new UserResponse();
		userRes.setUid(user.getUid());
		userRes.setName(user.getName());
		userRes.setEmail(user.getEmail());

		logger.info("user found successfully ..");
		return userRes;

	}

	// LOGIN LOGIC

	@Override
	public LoginResponse login(String email, String password) {

		User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User NOT FOUND"));

		if (!passwordEncoder.matches(password, user.getPassword())) {
			logger.warn("Invalid Password for email " + email);
			throw new RuntimeException("Invalid Credentials");
		}

		String token = jwtService.generateToken(user.getEmail(), user.getUid(), user.getUserRole().name());

		LoginResponse response = new LoginResponse();
		response.setToken(token);
		response.setUserId(user.getUid());
		response.setRole(user.getUserRole().name());

		logger.info("Login successful for email: {}", email);

		return response;
	}

	// for response LOGIC

	@Override
	public AdminResponse getUserDetailsById(Long uid) {

		// get USER from entity table
		User user = userRepo.findById(uid).orElseThrow(() -> {
			logger.error("user not found");
			return new RuntimeException("User not found");
		});

		// bind entity user to Dto
		AdminResponse response = new AdminResponse();
		response.setUid(user.getUid());
		response.setName(user.getName());
		response.setEmail(user.getEmail());
		response.setUserRole(user.getUserRole());

		// send data with dto
		logger.info("user Info send succesfully ..");
		return response;
	}

	@Override
	public String createAdmin(AdminRegisterRequest request) {

		if (userRepo.existsByEmail(request.getEmail())) {
			return "Email already registered";
		}

		User user = new User();

		user.setName(request.getName());
		user.setEmail(request.getEmail());

		// Password ko BCrypt se encode karo
		user.setPassword(passwordEncoder.encode(request.getPassword()));

		// ADMIN forcefully set hoga
		user.setUserRole(UserRole.ADMIN);

		userRepo.save(user);
		logger.info("admin register succesfully..");
		return "";
	}

}
