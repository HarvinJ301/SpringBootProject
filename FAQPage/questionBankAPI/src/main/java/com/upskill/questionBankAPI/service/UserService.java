package com.upskill.questionBankAPI.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upskill.questionBankAPI.model.User;
import com.upskill.questionBankAPI.repository.UserRepo;

/**
 * User Service that holds all the methods and functionality from the User Repo.
 */
@Service
public class UserService {
	
	/**
	 * retrieving the user repo class 
	 */
	@Autowired
	private UserRepo userRepo;
	
	/**
	 * method to find all users using the in-built method within the repo.
	 * @return all users
	 */
	public List<User> findAllUsers(){
		return userRepo.findAll();
	}
	
	/**
	 * method to get user by id, using a repo method of find by id. 
	 * if the id is incorrect or not retrievable then an error is shown.
	 * @param id
	 * @return
	 */
	public User findUserByID(int id) {
		Optional<User> userOpt = userRepo.findById(id);
		if(userOpt.isEmpty()) {
			System.out.println("User with this ID not found");
		}
		return userOpt.get();
	}
	
	/**
	 * method to find user by username by taking in the parameter username and returning the username found
	 * if the string passed in returns empty then an error is thrown.
	 * @param username
	 * @return
	 */
	public User findUserByUsername(String username) {
		Optional<User> user = userRepo.findByUsername(username);
		if(user.isEmpty()) {
			System.out.println("User with this username not found");
		}
		return user.get();
	}

}
