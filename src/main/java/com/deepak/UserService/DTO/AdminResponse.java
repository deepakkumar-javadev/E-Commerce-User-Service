package com.deepak.UserService.DTO;

import com.deepak.UserService.Entity.UserRole;

import lombok.Data;

@Data
public class AdminResponse {

	private Long uid;
	private String name;
	private String email;
	private UserRole userRole;
}
