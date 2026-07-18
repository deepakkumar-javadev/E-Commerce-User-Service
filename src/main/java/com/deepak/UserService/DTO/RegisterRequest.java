package com.deepak.UserService.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
	
	@NotBlank(message="UserName is required")
	private String name;
	private String email;
	private String password;
}
