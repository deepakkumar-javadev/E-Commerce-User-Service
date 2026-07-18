package com.deepak.UserService.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deepak.UserService.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

	public Optional <User> findByEmail(String Email);
	
	
	
}
