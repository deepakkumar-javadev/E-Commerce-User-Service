package com.deepak.UserService.DTO;

import com.deepak.UserService.Entity.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class AdminRegisterRequest {

	@NotBlank(message = "UserName is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
    
    private UserRole userRole;
}
