package com.deepak.UserService.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users", indexes = {
		@Index(name = "idx_users_email", columnList = "Email_ID"),
		@Index(name = "idx_users_role", columnList = "Role") })
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Uid")
	private Long uid;

	@Column(name = "Uname")
	private String name;

	@Column(name = "Email_ID")
	private String email;

	@Column(name = "PASSWORD")
	private String password;

	// some external data we want to store in table
	@Enumerated(EnumType.STRING)
	@Column(name = "Role")
	private UserRole userRole;

}
