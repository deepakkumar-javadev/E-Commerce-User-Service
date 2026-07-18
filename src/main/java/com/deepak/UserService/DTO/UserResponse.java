package com.deepak.UserService.DTO;

import lombok.Data;

// DTO fOR sending response to user


@Data
public class UserResponse {

	private Integer uid;
	private String name;
	private String email;
	private String role;
	
	
}
