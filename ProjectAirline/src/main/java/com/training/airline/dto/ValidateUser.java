package com.training.airline.dto;

import java.io.Serializable;

/**
 * This is a Dto class to hold validate user information. It has private fields
 * to represent the properties of each User. It has getters and setters to
 * access and modify the private fields.
 * 
 * @author 251656
 */
public class ValidateUser implements Serializable {

	/**
	 * default serialVersionUID is used here.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This field userId of type Integer represents the user id allocated to each
	 * user.
	 */
	private Integer userId;

	/**
	 * This field name of type String represents the name given for a user.
	 */
	private String password;

	/**
	 * This is a zero argument constructor. Used to instantiate validate user dto
	 * object.
	 */
	public ValidateUser() {

	}

	/**
	 * This is a parameterized constructor. Takes in the following parameters to
	 * instantiate the validate user dto object and then initialize the values with
	 * the arguments passed.
	 * 
	 * @param userId   ---> id of the user
	 * @param password ---> password of the user
	 */
	public ValidateUser(Integer userId, String password) {
		this.userId = userId;
		this.password = password;
	}

	/**
	 * This is a method used to get the user id from a validate user dto object
	 * 
	 * @return the user id given for each user.
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * This is a method used to set the user id for a validate user dto object
	 * 
	 * @param userId The id of the user is passed here.
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * This is a method used to get the user password from a validate user dto
	 * object
	 * 
	 * @return the user password given for each user.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * This is a method used to set the user password for a validate user dto object
	 * 
	 * @param password The password of the user is passed here.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
