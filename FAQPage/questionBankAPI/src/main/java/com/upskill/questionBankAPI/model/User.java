package com.upskill.questionBankAPI.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * the user entity to allow a log in feature
 */
@Entity
@Table(name="USER_TABLE")
public class User {
	
	/**
	 * User entity unique id.
	 */
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_gen")
	@SequenceGenerator(name = "user_id_gen", sequenceName = "user_id_seq", initialValue = 1, allocationSize = 1)
	private int userID;
	/**
	 * parameter for user entity for holding the first name of the user.
	 */
	private String firstName;
	/**
	 * parameter for user entity for holding the last name of the user.
	 */
	private String lastName;
	/**
	 * parameter for user entity for holding the email of the user.
	 */
	private String email;
	/**
	 * parameter for user entity for holding the username of the user.
	 */
	private String username;
	/**
	 * parameter for user entity for holding the password of the user.
	 */
	private String password;
	/**
	 * parameter for user entity for holding the user type of the user. Allowing different users to have different permissions.
	 */
	private String userType;

	
	public User() {}

	public User(String firstName, String lastName, String email, String username, String password, String userType,
			List<Question> favouriteQuestions) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.userType = userType;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
