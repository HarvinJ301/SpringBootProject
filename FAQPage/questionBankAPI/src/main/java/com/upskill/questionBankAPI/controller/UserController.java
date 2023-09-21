package com.upskill.questionBankAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upskill.questionBankAPI.model.User;
import com.upskill.questionBankAPI.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * controller that holds all the http requests and the url's that correspond to each method. 
 */
@RestController
@RequestMapping("api/v1/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	
	/**
	 * retrieving infromation from the user service class.
	 */
	@Autowired
	private UserService userService;
	
	@Operation(
		summary = "Find and returns all the users in the database.",
		description = "Looks through the database for all the users inside it and returning them as a response.",
		method = "GET",
		responses = {
			@ApiResponse(
				responseCode = "200", 
				description = "All users are retrieved that are expected and outputted as a JSON in the body of the response.", 
				content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}
			),
			@ApiResponse(
				responseCode = "404",
				description = "users are not retrieved from the database and nothing is returned.",
				content = {@Content(mediaType = MediaType.TEXT_PLAIN_VALUE)}
			),
		}
	)
	/**
	 * returns a list of all users. Uses the method find all users from the user service.
	 * @return
	 */
	@GetMapping
	public List<User> getUsers(){
		return userService.findAllUsers();
	}
	
	@Operation(
		summary = "Finds and returns a list of users by id",
		description = "Looks through the database to find the user matched with the id value passed through.",
		method = "GET",	
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "User is retrieved successfully and returned as a JSON object in the body of the response.",
				content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}
			),
			@ApiResponse(
				responseCode = "404",
				description = "User is not found with the given id value.",
				content = {@Content(mediaType = MediaType.TEXT_PLAIN_VALUE)}
			),
		}
	)
	/**
	 * get user by id method from user service taking in the id specifed.
	 * @param id
	 * @return
	 */
	@GetMapping("{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id){
		return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByID(id));
	}
	
	@Operation(
		summary = "Searches and returns a list of users by the value the user inputs.",
		description = "Looks through the database through the database to find the users matching with the value passed through.",
		method = "GET",	
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Users are retrieved successfully and returned as a JSON object in the body of the response.",
				content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}
			),
			@ApiResponse(
				responseCode = "404",
				description = "User is not found with the given value.",
				content = {@Content(mediaType = MediaType.TEXT_PLAIN_VALUE)}
			),
		}
	)
	/**
	 * username search functionality following the url provided, takes in the parameters and returns matching values.
	 * @param username
	 * @return
	 */
	@GetMapping("/username/{username}")
	public ResponseEntity<User> getUserById(@PathVariable String username){
		return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByUsername(username));
	}

}
