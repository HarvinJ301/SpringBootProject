package com.upskill.questionBankAPI.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upskill.questionBankAPI.model.User;

/**
 * User repository taking in the User model and id and holding the methods that allow the filtering of Users
 */
public interface UserRepo extends JpaRepository<User, Integer>{
	
	/**
	 * Method that returns the value of User and takes a string parameter of username to find mathes to the string within the user database.
	 */
	Optional<User> findByUsername(String username);

}
