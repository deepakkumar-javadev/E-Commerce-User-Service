package com.deepak.UserService.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="user_table")
@Data
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Uid")
	private Integer uid;
	
	@Column(name="Uname")
	private String name;
	
	@Column(name="Email_ID")
	private String email;
	
	@Column(name="PASSWORD")
	private String password;
	
	// some external data we want to store in table
	@Column(name="Role")
	private String userRole;
	
	
	
}
