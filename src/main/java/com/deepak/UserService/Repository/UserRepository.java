package com.deepak.UserService.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deepak.UserService.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	public Optional <User> findByEmail(String Email);
	
	public boolean existsByEmail(String email);
	
}
