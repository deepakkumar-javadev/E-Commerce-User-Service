package com.deepak.UserService.DTO;

import lombok.Data;

@Data
public class LoginResponse {

	private String token;
    private Long userId;
    private String role;
}
